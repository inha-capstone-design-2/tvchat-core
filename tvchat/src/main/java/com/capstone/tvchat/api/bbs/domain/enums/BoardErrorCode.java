package com.capstone.tvchat.api.bbs.domain.enums;

import lombok.Getter;

@Getter
public enum BoardErrorCode {
    BOARD_NOT_FOUND("BOARD_NOT_FOUND", "게시판을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    BoardErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
