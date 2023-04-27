package com.capstone.tvchat.api.episode.domain.enums;

import lombok.Getter;

@Getter
public enum EpisodeErrorCode {
    EPISODE_NOT_FOUND("EPISODE_NOT_FOUND", "에피소드를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    EpisodeErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
