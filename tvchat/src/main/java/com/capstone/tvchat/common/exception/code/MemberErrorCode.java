package com.capstone.tvchat.common.exception.code;

import lombok.Getter;

@Getter
public enum MemberErrorCode {

    ENTERED_EMAIL_AND_PASSWORD("ENTERED_EMAIL_AND_PASSWORD","잘못된 이메일, 패스워드 형식입니다.");

    private String code;

    private String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
