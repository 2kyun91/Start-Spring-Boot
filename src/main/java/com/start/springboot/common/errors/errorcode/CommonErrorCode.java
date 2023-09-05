package com.start.springboot.common.errors.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청 결과가 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 발생."),
    FILE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "지정된 경로를 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
