package com.capstone.tvchat.api.episode.service;

import com.capstone.tvchat.api.bbs.domain.enums.BoardErrorCode;
import com.capstone.tvchat.api.episode.domain.dto.request.CreateEpisodeRequest;
import com.capstone.tvchat.api.episode.domain.dto.response.EpisodeResponse;
import com.capstone.tvchat.api.episode.domain.entity.Episode;
import com.capstone.tvchat.api.episode.domain.enums.EpisodeErrorCode;
import com.capstone.tvchat.api.episode.repository.EpisodeRepository;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.domain.enums.ProgramErrorCode;
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
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public Long createEpisode(CreateEpisodeRequest createEpisodeRequest) {
        Program program = programRepository.findById(createEpisodeRequest.getProgramId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Episode episode = CreateEpisodeRequest.toEntity(program, createEpisodeRequest);

        return episodeRepository.save(episode)
                .getId();
    }

    @Transactional
    public void deleteEpisode(Long episodeId) {
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(EpisodeErrorCode.EPISODE_NOT_FOUND.getMessage())
                        .errorCode(EpisodeErrorCode.EPISODE_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        episode.delete();
    }

    public EpisodeResponse getEpisode(Long episodeId) {
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(EpisodeErrorCode.EPISODE_NOT_FOUND.getMessage())
                        .errorCode(EpisodeErrorCode.EPISODE_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return EpisodeResponse.toResponse(episode);
    }

    public List<EpisodeResponse> getEpisodeByProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(ProgramErrorCode.PROGRAM_NOT_FOUND.getMessage())
                        .errorCode(ProgramErrorCode.PROGRAM_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        List<EpisodeResponse> episodeResponseList = episodeRepository.findByProgram(program).stream()
                .map(EpisodeResponse::toResponse)
                .collect(Collectors.toList());

        return episodeResponseList;
    }
}
