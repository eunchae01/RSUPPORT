package com.rsupport.notice.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsupport.notice.exception.CommonException;
import com.rsupport.notice.exception.ErrCode;
import jakarta.annotation.Nullable;
import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Builder
public record ResponseDTO<T>(@JsonIgnore HttpStatus httpStatus, // 50000
                             @NotNull Boolean success,          // true, false
                             @Nullable T data,                  //
                             @Nullable ExceptionDTO error) {

    //static 으로 인한 공용 사용 가능

    //성공시
    public static <T> ResponseDTO<T> ok(@Nullable T data) { //성공
        return new ResponseDTO<T>(HttpStatus.OK, true, data, null);
    }

    public static <T> ResponseDTO<T> created(@Nullable final T data) {
        return new ResponseDTO<>(HttpStatus.CREATED, true, data, null);
    }

    //실패시
    public static ResponseDTO<Object> fail(final HandlerMethodValidationException e) { //실패한 경우
        return new ResponseDTO<>(HttpStatus.BAD_REQUEST, false, null, new ExceptionDTO(ErrCode.MISSING_REQUEST_PARAMETER));
    }

    public static ResponseDTO<Object> fail(final CommonException e) { //실패한 경우
        return new ResponseDTO<>(e.getErrorCode().getHttpStatus(), false, null, new ExceptionDTO(e.getErrorCode()));
    }

    public static ResponseDTO<Object> fail(final MethodArgumentNotValidException e) {
        return new ResponseDTO<>(HttpStatus.BAD_REQUEST, false, null, new ArgumentNotValidExceptionDTO(e));
    }

    public static ResponseDTO<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, false, null, new ExceptionDTO(ErrCode.INVALID_PARAMETER));
    }

    public static ResponseDTO<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDTO<>(HttpStatus.BAD_REQUEST, false, null, new ExceptionDTO(ErrCode.MISSING_REQUEST_PARAMETER));
    }

    public static ResponseDTO<Object> fail(final HttpMessageNotReadableException e) {
        return new ResponseDTO<>(HttpStatus.BAD_REQUEST, false, null, new ExceptionDTO(ErrCode.NOTICE_NOT_FOUND));
    }
}