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

    @Builder
    public ProgramResponse(Long programId, String programTitle) {
        this.programId = programId;
        this.programTitle = programTitle;
    }

    public static ProgramResponse toResponse(Program program) {
        return ProgramResponse.builder()
                .programId(program.getId())
                .programTitle(program.getTitle())
                .build();
    }
}
