package com.capstone.tvchat.api.report.domain.dto.response;

import com.capstone.tvchat.api.report.domain.entity.Report;
import com.capstone.tvchat.api.report.domain.enums.ReportStatus;
import com.capstone.tvchat.api.report.domain.enums.ReportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReportResponse {
    private Long reportId;
    private ReportType reportType;
    private String title;
    private String content;
    private ReportStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;
    private Long createdBy;
    private Long updatedBy;

    @Builder
    public ReportResponse(Long reportId, ReportType reportType, String title, String content, ReportStatus status, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long createdBy, Long updatedBy) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public static ReportResponse toResponse(Report report) {
        return ReportResponse.builder()
                .reportId(report.getId())
                .reportType(report.getType())
                .title(report.getTitle())
                .content(report.getContent())
                .status(report.getStatus())
                .createdDate(report.getCreatedDate())
                .lastModifiedDate(report.getLastModifiedDate())
                .createdBy(report.getCreatedBy())
                .updatedBy(report.getUpdatedBy())
                .build();
    }
}
