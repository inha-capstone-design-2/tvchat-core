package com.capstone.tvchat.api.episode.domain.dto.request;

import com.capstone.tvchat.api.episode.domain.entity.Episode;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Builder;

import java.time.LocalDateTime;

public class CreateEpisodeRequest {
    private Long programId;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public CreateEpisodeRequest(Long programId, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.programId = programId;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Episode toEntity(Program program, CreateEpisodeRequest createEpisodeRequest) {
        return Episode.createBuilder()
                .program(program)
                .description(createEpisodeRequest.description)
                .startTime(createEpisodeRequest.startTime)
                .endTime(createEpisodeRequest.endTime)
                .build();
    }
}
