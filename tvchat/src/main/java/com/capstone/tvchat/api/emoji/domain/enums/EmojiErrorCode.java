package com.capstone.tvchat.api.emoji.domain.enums;


import lombok.Getter;

@Getter
public enum EmojiErrorCode {
    EMOJI_NOT_FOUND("EMOJI_NOT_FOUND", "이모지를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    EmojiErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
