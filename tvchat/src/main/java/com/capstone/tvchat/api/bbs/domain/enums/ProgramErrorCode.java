package com.capstone.tvchat.api.bbs.domain.enums;

import lombok.Getter;

@Getter
public enum ProgramErrorCode {
    PROGRAM_NOT_FOUND("PROGRAM_NOT_FOUND", "프로그램을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ProgramErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
