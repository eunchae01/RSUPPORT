package com.rsupport.notice.dto.exception;

import com.rsupport.notice.exception.ErrCode;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ArgumentNotValidExceptionDTO extends ExceptionDTO{
    private final Map<String, String> errorFields;

    public ArgumentNotValidExceptionDTO(final MethodArgumentNotValidException methodArgumentNotValidException) {
        super(ErrCode.INVALID_PARAMETER);

        this.errorFields = new HashMap<>();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors().forEach(e -> this.errorFields.put(((FieldError) e).getField(), e.getDefaultMessage()));
    }
}
