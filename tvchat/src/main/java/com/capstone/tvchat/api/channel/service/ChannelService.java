package com.capstone.tvchat.api.channel.service;

import com.capstone.tvchat.api.channel.domain.dto.ChannelSearchRequest;
import com.capstone.tvchat.api.channel.domain.dto.response.ChannelResponse;
import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
