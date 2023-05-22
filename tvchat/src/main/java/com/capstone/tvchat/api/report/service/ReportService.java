package com.capstone.tvchat.api.report.service;

import com.capstone.tvchat.api.report.domain.dto.request.CreateReportRequest;
import com.capstone.tvchat.api.report.domain.dto.request.ModifyReportRequest;
import com.capstone.tvchat.api.report.domain.dto.response.ReportResponse;
import com.capstone.tvchat.api.report.domain.entity.Report;
import com.capstone.tvchat.api.report.domain.enums.ReportErrorCode;
import com.capstone.tvchat.api.report.repository.ReportRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {
    private final ReportRepository reportRepository;

    public List<ReportResponse> getReports() {
        return reportRepository.findAll().stream()
                .map(ReportResponse::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReportResponse> getMyReports(Long memberId) {
        return reportRepository.findByCreatedBy(memberId).stream()
                .map(ReportResponse::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long modifyReport(ModifyReportRequest modifyReportRequest) {
        Report report = reportRepository.findById(modifyReportRequest.getId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ReportErrorCode.REPORT_NOT_FOUND.getMessage())
                        .errorCode(ReportErrorCode.REPORT_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        report.modifyReport(modifyReportRequest);

        return report.getId();
    }

    @Transactional
    public Long createReport(CreateReportRequest createReportRequest) {
        return reportRepository.save(
                Report.create(createReportRequest))
                .getId();
    }
}
