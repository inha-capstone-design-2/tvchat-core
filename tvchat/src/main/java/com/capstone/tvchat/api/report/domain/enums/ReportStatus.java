package com.capstone.tvchat.api.report.domain.enums;

import lombok.Getter;

@Getter
public enum ReportStatus {
    WAITING,
    ON_PROGRESS,
    DONE,
    CANCELLED;
}
