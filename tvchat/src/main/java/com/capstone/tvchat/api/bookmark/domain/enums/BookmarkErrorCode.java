package com.capstone.tvchat.api.bookmark.domain.enums;

import lombok.Getter;

@Getter
public enum BookmarkErrorCode {
    BOOKMARK_NOT_FOUND("BOOKMARK_NOT_FOUND", "즐겨찾기를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    BookmarkErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
