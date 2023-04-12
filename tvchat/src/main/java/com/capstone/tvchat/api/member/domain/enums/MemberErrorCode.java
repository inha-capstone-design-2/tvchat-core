package com.capstone.tvchat.api.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberErrorCode {
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    NO_EMAIL_OR_PASSWORD("NO_EMAIL_OR_PASSWORD", "이메일이나 패스워드를 확인해주세요");


    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
