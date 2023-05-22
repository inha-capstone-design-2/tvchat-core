package com.capstone.tvchat.api.report.domain.entity;

import com.capstone.tvchat.api.report.domain.dto.request.CreateReportRequest;
import com.capstone.tvchat.api.report.domain.dto.request.ModifyReportRequest;
import com.capstone.tvchat.api.report.domain.enums.ReportStatus;
import com.capstone.tvchat.api.report.domain.enums.ReportType;
import com.capstone.tvchat.common.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "REPORT")
public class Report extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type")
    private ReportType type;

    @Column(name = "report_title")
    private String title;

    @Column(name = "report_content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status")
    private ReportStatus status;

    @Builder
    public Report(Long id, ReportType type, String title, String content, ReportStatus status) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public static Report create(CreateReportRequest createReportRequest) {
        return Report.builder()
                .type(createReportRequest.getType())
                .title(createReportRequest.getTitle())
                .content(createReportRequest.getContent())
                .status(ReportStatus.WAITING)
                .build();
    }

    public void delete() {
        this.status=ReportStatus.CANCELLED;
    }

    public void modifyReport(ModifyReportRequest modifyReportRequest) {
        this.type = modifyReportRequest.getType();
        this.title = modifyReportRequest.getTitle();
        this.content = modifyReportRequest.getContent();
    }
}
