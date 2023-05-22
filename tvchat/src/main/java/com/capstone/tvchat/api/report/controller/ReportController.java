package com.capstone.tvchat.api.report.controller;

import com.capstone.tvchat.api.report.domain.dto.request.CreateReportRequest;
import com.capstone.tvchat.api.report.domain.dto.request.ModifyReportRequest;
import com.capstone.tvchat.api.report.service.ReportService;
import com.capstone.tvchat.common.result.ResponseHandler;
import com.capstone.tvchat.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("신고 API Controller")
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @ApiOperation(value = "신고 전체 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getReports() {
        return ResponseHandler.generate()
                .data(reportService.getReports())
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "사용자가 보낸 신고 조회 API")
    @GetMapping("/my")
    public ResponseEntity<?> getMyReports() {
        Long memberId = SecurityUtil.getCurrentMemberId();

        return ResponseHandler.generate()
                .data(reportService.getMyReports(memberId))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "신고 수정 API")
    @PatchMapping("/")
    public ResponseEntity<?> modifyReport(
            @RequestBody ModifyReportRequest modifyReportRequest
    ) {
        return ResponseHandler.generate()
                .data(reportService.modifyReport(modifyReportRequest))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "신고 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createReport(
            @RequestBody CreateReportRequest createReportRequest
    ) {
        return ResponseHandler.generate()
                .data(reportService.createReport(createReportRequest))
                .status(HttpStatus.CREATED)
                .build();
    }
}
