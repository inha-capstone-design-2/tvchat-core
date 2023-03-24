package com.capstone.tvchat.api.program.controller;

import com.capstone.tvchat.api.program.service.ProgramService;
import com.capstone.tvchat.common.domain.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/program")
@Api("프로그램 API Controller")
public class ProgramController {
    private final ProgramService programService;

    @ApiOperation(value = "프로그램 전체 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getAllPrograms() {
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(programService.getAllPrograms())
                        .build()
        );
    }

    @ApiOperation(value = "채널 별 프로그램 조회 API")
    @GetMapping("/{channel-id}")
    public ResponseEntity<?> getProgramByChannelId(@RequestParam("channel-id") Long channelId) {
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(programService.getProgramByChannelId(channelId))
                        .build()
        );
    }


}
