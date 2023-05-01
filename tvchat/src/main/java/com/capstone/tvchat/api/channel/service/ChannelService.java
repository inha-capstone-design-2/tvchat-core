package com.capstone.tvchat.api.channel.service;

import com.capstone.tvchat.api.channel.domain.dto.request.CreateChannelRequest;
import com.capstone.tvchat.api.channel.domain.dto.request.ModifyChannelRequest;
import com.capstone.tvchat.api.channel.domain.dto.response.ChannelResponse;
import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.channel.domain.enums.ChannelErrorCode;
import com.capstone.tvchat.api.channel.repository.ChannelRepository;
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
public class ChannelService {

    private final ChannelRepository channelRepository;

    public List<ChannelResponse> getChannels() {
        return channelRepository.findAll().stream()
                .map(ChannelResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createChannel(CreateChannelRequest createChannelRequest) {
        return channelRepository.save(
                CreateChannelRequest.toEntity(createChannelRequest))
                .getId();
    }

    @Transactional
    public void deleteChannel(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                        .orElseThrow(() -> ApiException.builder()
                                .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                                .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                                .status(HttpStatus.BAD_REQUEST)
                                .build());

        channel.delete();
    }

    @Transactional
    public ChannelResponse modifyChannel(Long channelId, ModifyChannelRequest modifyChannelRequest) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .build());

        channel.modifyChannel(modifyChannelRequest);

        return ChannelResponse.toResponse(channel);
    }
}
