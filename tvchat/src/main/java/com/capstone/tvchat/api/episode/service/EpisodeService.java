package com.capstone.tvchat.api.episode.service;

import com.capstone.tvchat.api.bbs.domain.enums.BoardErrorCode;
import com.capstone.tvchat.api.episode.domain.dto.request.CreateEpisodeRequest;
import com.capstone.tvchat.api.episode.domain.dto.response.EpisodeResponse;
import com.capstone.tvchat.api.episode.domain.entity.Episode;
import com.capstone.tvchat.api.episode.domain.enums.EpisodeErrorCode;
import com.capstone.tvchat.api.episode.repository.EpisodeRepository;
import com.capstone.tvchat.api.program.domain.dto.response.ProgramResponse;
import com.capstone.tvchat.api.program.domain.entity.Program;
import com.capstone.tvchat.api.program.repository.ProgramRepository;
import com.capstone.tvchat.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public void createEpisode(CreateEpisodeRequest createEpisodeRequest) {
        Program program = programRepository.findById(createEpisodeRequest.getProgramId())
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.BOARD_NOT_FOUND.getMessage())
                        .errorCode(BoardErrorCode.BOARD_NOT_FOUND.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        episodeRepository.save(CreateEpisodeRequest.toEntity(program, createEpisodeRequest));
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

        return EpisodeResponse.toResponse(episode, ProgramResponse.toResponse(episode.getProgram()));
    }

    public Object getEpisodeByProgram(Long programId) {
        return null;
    }
}
