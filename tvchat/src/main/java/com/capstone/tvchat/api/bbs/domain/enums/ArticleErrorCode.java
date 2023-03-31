package com.capstone.tvchat.api.bbs.domain.enums;

import lombok.Getter;

@Getter
public enum ArticleErrorCode {
    ARTICLE_NOT_FOUND("ARTICLE_NOT_FOUND", "게시글을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ArticleErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
