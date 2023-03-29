package com.capstone.tvchat.api.program.service;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.channel.domain.enums.ChannelErrorCode;
import com.capstone.tvchat.api.channel.repository.ChannelRepository;
import com.capstone.tvchat.api.program.domain.dto.response.ProgramResponse;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ChannelRepository channelRepository;

    public List<ProgramResponse> getAllPrograms() {
        return programRepository.findAll().stream()
                .map(ProgramResponse::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProgramResponse> getProgramByChannelId(Long channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> ApiException.builder()
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .build());

        return programRepository.findByChannel(channel).stream()
                .map(ProgramResponse::toResponse)
                .collect(Collectors.toList());
    }


}
