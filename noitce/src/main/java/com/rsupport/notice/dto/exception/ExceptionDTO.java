package com.rsupport.notice.dto.exception;

import com.rsupport.notice.exception.ErrCode;
import lombok.Getter;

@Getter
public class ExceptionDTO {
    private final String code;
    private final String message;

    public ExceptionDTO(ErrCode errCode) {
        this.code = errCode.getCode();
        this.message = errCode.getMessage();
    }
}
