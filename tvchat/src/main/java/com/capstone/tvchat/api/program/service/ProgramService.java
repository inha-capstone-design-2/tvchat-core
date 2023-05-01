package com.capstone.tvchat.api.program.service;

import com.capstone.tvchat.api.program.domain.enums.ProgramErrorCode;
import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.channel.domain.enums.ChannelErrorCode;
import com.capstone.tvchat.api.channel.repository.ChannelRepository;
import com.capstone.tvchat.api.program.domain.dto.request.CreateProgramRequest;
import com.capstone.tvchat.api.program.domain.dto.request.ModifyProgramRequest;
import com.capstone.tvchat.api.program.domain.dto.request.ProgramSearch;
import com.capstone.tvchat.api.program.domain.dto.response.ProgramResponse;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
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


    @Transactional
    public Long createProgram(CreateProgramRequest createProgramRequest) {
        Channel channel = channelRepository.findById(createProgramRequest.getChannelId())
                .orElseThrow(() -> ApiException.builder()
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .build());

        return programRepository.save(
                CreateProgramRequest.toEntity(createProgramRequest, channel)
        ).getId();
    }

    @Transactional
    public ProgramResponse modifyProgram(Long programId, ModifyProgramRequest modifyProgramRequest) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ProgramErrorCode.PROGRAM_NOT_FOUND.getMessage())
                        .errorCode(ProgramErrorCode.PROGRAM_NOT_FOUND.getCode())
                        .build());

        Channel channel = channelRepository.findById(modifyProgramRequest.getChannelId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ChannelErrorCode.CHANNEL_NOT_FOUND.getMessage())
                        .errorCode(ChannelErrorCode.CHANNEL_NOT_FOUND.getCode())
                        .build());

        program.modifyProgram(modifyProgramRequest.getTitle(), channel);

        return ProgramResponse.toResponse(program);
    }

    @Transactional
    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ProgramErrorCode.PROGRAM_NOT_FOUND.getMessage())
                        .errorCode(ProgramErrorCode.PROGRAM_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        program.delete();
    }

    public List<ProgramResponse> searchProgram(ProgramSearch programSearch) {
        return null;
    }
}
