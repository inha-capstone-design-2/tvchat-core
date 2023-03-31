package com.capstone.tvchat.api.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberErrorCode {
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "사용자를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
