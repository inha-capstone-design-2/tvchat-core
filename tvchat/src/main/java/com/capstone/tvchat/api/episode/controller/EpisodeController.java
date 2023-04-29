package com.capstone.tvchat.api.episode.controller;

import com.capstone.tvchat.api.episode.domain.dto.request.CreateEpisodeRequest;
import com.capstone.tvchat.api.episode.service.EpisodeService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("회차 API Controller")
@RequestMapping("/api/episode")
public class EpisodeController {
    private final EpisodeService episodeService;

    @ApiOperation(value = "지정 회차 조회 API")
    @GetMapping("/{episode-id}")
    public ResponseEntity<?> getEpisode(@RequestParam(name = "episode-id")Long episdoeId) {
        return ResponseHandler.generate()
                .data(episodeService.getEpisode(episdoeId))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "프로그램 별 회차 조회 API")
    @GetMapping("/{program-id}")
    public ResponseEntity<?> getEpisodeByProgram(@RequestParam(name = "program-id")Long programId) {
        return ResponseHandler.generate()
                .data(episodeService.getEpisodeByProgram(programId))
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "회차 생성 API")
    @PostMapping("/")
    public ResponseEntity<?> createEpisode(@RequestBody CreateEpisodeRequest createEpisodeRequest) {
        episodeService.createEpisode(createEpisodeRequest);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "회차 삭제 API")
    @DeleteMapping("/{episode-id}")
    public ResponseEntity<?> deleteEpisode(@RequestParam(name = "episode-id")Long episodeId) {
        episodeService.deleteEpisode(episodeId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }
}
