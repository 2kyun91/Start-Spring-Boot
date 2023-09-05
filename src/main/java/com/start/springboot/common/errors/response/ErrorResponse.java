package com.start.springboot.common.errors.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.errorcode.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;

    // 에러 발생한 필드 응답, errors가 빈 객체인경우 응답 하지 않도록 설정
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
