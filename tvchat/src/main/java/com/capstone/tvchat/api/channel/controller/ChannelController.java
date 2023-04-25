package com.capstone.tvchat.api.channel.controller;

import com.capstone.tvchat.api.channel.domain.dto.request.CreateChannelRequest;
import com.capstone.tvchat.api.channel.domain.dto.request.ModifyChannelRequest;
import com.capstone.tvchat.api.channel.service.ChannelService;
import com.capstone.tvchat.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("채널 API Controller")
@RequestMapping("/api/channel")
public class ChannelController {

    private final ChannelService channelService;

    @ApiOperation(value = "채널 전체 조회 API")
    @GetMapping("/")
    public ResponseEntity<?> getChannels() {
        return ResponseHandler.generate()
                .data(channelService.getChannels())
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "채널 등록 API")
    @PostMapping("/")
    public ResponseEntity<?> createChannel(@RequestBody CreateChannelRequest createChannelRequest) {
        return ResponseHandler.generate()
                .data(channelService.createChannel(createChannelRequest))
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "채널 삭제 API")
    @DeleteMapping("/{channel-id}")
    public ResponseEntity<?> deleteChannel(@RequestParam(name = "channel-id")Long channelId) {
        channelService.deleteChannel(channelId);

        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "채널 수정 API")
    @PatchMapping("/{channel-id}")
    public ResponseEntity<?> modifyChannel(@RequestParam(name = "channel-id")Long channelId, ModifyChannelRequest modifyChannelRequest) {
        return ResponseHandler.generate()
                .data(channelService.modifyChannel(channelId, modifyChannelRequest))
                .status(HttpStatus.OK)
                .build();
    }
}
