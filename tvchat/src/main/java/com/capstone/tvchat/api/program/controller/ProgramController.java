package com.capstone.tvchat.api.program.controller;

import com.capstone.tvchat.api.program.domain.dto.request.CreateProgramRequest;
import com.capstone.tvchat.api.program.domain.dto.request.ModifyProgramRequest;
import com.capstone.tvchat.api.program.domain.dto.request.ProgramSearch;
import com.capstone.tvchat.api.program.service.ProgramService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/program")
@Api("프로그램 API Controller")
public class ProgramController {
    private final ProgramService programService;

    @ApiOperation(value = "프로그램 전체 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getAllPrograms() {
        return ResponseHandler.generate()
                        .data(programService.getAllPrograms())
                        .status(HttpStatus.OK)
                        .build();
    }

    @ApiOperation(value = "채널 별 프로그램 조회 API")
    @GetMapping("/{channel-id}")
    public ResponseEntity<?> getProgramByChannelId(@RequestParam("channel-id") Long channelId) {
        return ResponseHandler.generate()
                .data(programService.getProgramByChannelId(channelId))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "프로그램 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createProgram(@RequestBody CreateProgramRequest createProgramRequest) {
        return ResponseHandler.generate()
                .data(programService.createProgram(createProgramRequest))
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "프로그램 수정 API")
    @PatchMapping("/{program-id}")
    public ResponseEntity<?> modifyProgram(@RequestBody ModifyProgramRequest modifyProgramRequest,
                                           @RequestParam(name = "program-id") Long programId) {
        return ResponseHandler.generate()
                .data(programService.modifyProgram(programId, modifyProgramRequest))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "프로그램 삭제 API")
    @DeleteMapping("/{program-id}")
    public ResponseEntity<?> deleteProgram(@RequestParam(name = "program-id") Long programId) {
        programService.deleteProgram(programId);

        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "프로그램 검색 API")
    @GetMapping("/search")
    public ResponseEntity<?> searchProgram(@RequestBody ProgramSearch programSearch) {
       return ResponseHandler.generate()
               .data(programService.searchProgram(programSearch))
               .status(HttpStatus.OK)
               .build();
    }
}
