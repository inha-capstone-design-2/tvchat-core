package com.capstone.tvchat.api.program.domain.dto.response;

import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramResponse {
    private Long programId;
    private String programTitle;
    private Long channelId;

    @Builder
    public ProgramResponse(Long programId, String programTitle, Long channelId) {
        this.programId = programId;
        this.programTitle = programTitle;
        this.channelId = channelId;
    }

    public static ProgramResponse toResponse(Program program) {
        return ProgramResponse.builder()
                .programId(program.getId())
                .programTitle(program.getTitle())
                .channelId(program.getChannel().getId())
                .build();
    }
}
