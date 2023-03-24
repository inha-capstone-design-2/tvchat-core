package com.capstone.tvchat.common.exception;

import com.capstone.tvchat.common.domain.JsonResultData;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private JsonResultData errorEntity;

    private HttpStatus status;

    @Builder
    public ApiException(String errorMessage, String errorCode, HttpStatus status) {
        super(errorMessage);
        this.errorEntity = JsonResultData.failResultBuilder()
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .build();
        this.status = status;
    }
}
