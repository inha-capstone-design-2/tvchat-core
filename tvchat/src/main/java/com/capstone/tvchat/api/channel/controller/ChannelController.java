package com.capstone.tvchat.api.channel.controller;

import com.capstone.tvchat.api.channel.domain.dto.ChannelSearchRequest;
import com.capstone.tvchat.api.channel.domain.dto.request.ChannelCreateRequest;
import com.capstone.tvchat.api.channel.service.ChannelService;
import com.capstone.tvchat.common.BaseEntity.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequiredArgsConstructor
@Api("채널 API Controller")
@RequestMapping("/api/channel")
public class ChannelController {

    private final ChannelService channelService;

    @ApiOperation(value = "채널 전체 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getChannels() {
        return ResponseEntity.ok(
                JsonResultData.successResultBuilder()
                        .data(channelService.getChannels())
                        .build()
        );
    }

    @ApiOperation(value = "채널 등록 API")
    @PostMapping("/")
    public ResponseEntity<?> createChannel(@RequestBody ChannelCreateRequest channelCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(channelService.createChannel(channelCreateRequest))
                        .build()
                );
    }
}
