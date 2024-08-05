package com.rsupport.notice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrCode {
    //Bad Request Error
    INVALID_PARAMETER("40000", HttpStatus.BAD_REQUEST, "유효하지 않는 파라미터입니다."),
    MISSING_REQUEST_PARAMETER("40001", HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    INVALID_ROLE("40002", HttpStatus.BAD_REQUEST, "유효하지 않은 권한입니다."),
    INVALID_PROVIDER("40003", HttpStatus.BAD_REQUEST, "유효하지 않은 제공자입니다."),
    INVALID_HEADER("40004", HttpStatus.BAD_REQUEST, "유효하지 않은 헤더값입니다."),

    // Not Found Error
    NOTICE_NOT_FOUND("40401", HttpStatus.NOT_FOUND, "해당 게시물은 존재하지 않습니다."),
    ALREADY_DELETED("40402", HttpStatus.NOT_FOUND, "이미 삭제된 게시물입니다."),

    // UnsupportedMediaType Error
    UNSUPPORTED_MEDIA_TYPE("41500", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "허용되지 않은 파일 형식입니다."),

    // Server, File Up/DownLoad Error
    SERVER_ERROR("50000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}