package com.rsupport.notice.exception;

import com.rsupport.notice.dto.exception.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.rsupport.notice.exception.ErrCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 지원되지 않는 HTTP 메소드를 사용할 때 발생하는 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseDTO<?> handleNoPageFoundException(Exception e) {
        return ResponseDTO.fail(new CommonException(ErrCode.NOTICE_NOT_FOUND));
    }

    // @Validated 어노테이션을 사용하여 검증을 수행할 때 발생하는 예외
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseDTO<?> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseDTO.fail(e);
    }

    // 메소드의 인자 타입이 일치하지 않을 때 발생하는 예외
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseDTO<?> handleArgumentNotValidException(MethodArgumentTypeMismatchException e) {
        return ResponseDTO.fail(e);
    }

    // 필수 파라미터가 누락되었을 때 발생하는 예외
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseDTO<?> handleServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseDTO.fail(e);
    }

    // 필수 RequestPart가 누락되었을 때 발생하는 예외
    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseDTO<?> handleServletRequestParameterException(MissingServletRequestPartException e) {
        return ResponseDTO.fail(new CommonException(ErrCode.MISSING_REQUEST_PARAMETER));
    }

    // post 요청 body가 없을 때 발생하는 에러
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseDTO<?> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseDTO.fail(e);
    }

    // 개발자가 직접 정의한 예외
    @ExceptionHandler(value = {CommonException.class})
    public ResponseDTO<Object> handleApiException(CommonException e) {
        return ResponseDTO.fail(e);
    }

    // 서버, DB 예외
    @ExceptionHandler(value = {Exception.class})
    public ResponseDTO<?> handleException(Exception e) {
        e.printStackTrace();
        return ResponseDTO.fail(new CommonException(ErrCode.SERVER_ERROR));
    }
}
