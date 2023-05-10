package com.capstone.tvchat.api.episode.domain.dto.response;

import com.capstone.tvchat.api.episode.domain.entity.Episode;
import com.capstone.tvchat.api.program.domain.dto.response.ProgramResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EpisodeResponse {
    private Long id;
    private ProgramResponse program;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @Builder
    public EpisodeResponse(Long id, ProgramResponse program, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.program = program;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static EpisodeResponse toResponse(Episode episode) {
        return EpisodeResponse.builder()
                .id(episode.getId())
                .program(ProgramResponse.toResponse(episode.getProgram()))
                .description(episode.getDescription())
                .startTime(episode.getStartTime())
                .endTime(episode.getEndTime())
                .build();
    }
}
