package com.capstone.tvchat.api.report.domain.dto.request;

import com.capstone.tvchat.api.report.domain.enums.ReportType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyReportRequest {
    private Long id;
    private ReportType type;
    private String title;
    private String content;
}
