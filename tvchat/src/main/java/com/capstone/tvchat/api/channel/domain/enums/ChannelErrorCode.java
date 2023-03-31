package com.capstone.tvchat.api.channel.domain.enums;

import lombok.Getter;

@Getter
public enum ChannelErrorCode {
    CHANNEL_NOT_FOUND("CHANNEL_NOT_FOUND", "채널을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ChannelErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
