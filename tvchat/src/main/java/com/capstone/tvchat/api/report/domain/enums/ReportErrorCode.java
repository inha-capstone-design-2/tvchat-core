package com.capstone.tvchat.api.report.domain.enums;

import lombok.Getter;

@Getter
public enum ReportErrorCode {
    REPORT_NOT_FOUND("REPORT_NOT_FOUND", "신고를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ReportErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
