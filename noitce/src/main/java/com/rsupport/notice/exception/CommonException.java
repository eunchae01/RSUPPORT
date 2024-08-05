package com.rsupport.notice.exception;

public class CommonException extends RuntimeException{
    private final ErrCode errCode;

    public CommonException(ErrCode errCode) {
        this.errCode = errCode;
    }

    public ErrCode getErrorCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return errCode.getMessage();
    }
}
