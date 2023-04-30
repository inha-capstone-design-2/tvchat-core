package com.capstone.tvchat.api.bookmark.domain.dto.response;

import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import com.capstone.tvchat.api.program.domain.dto.response.ProgramResponse;
import lombok.Builder;

public class BookmarkResponse {
    private Long id;
    private ProgramResponse program;

    @Builder
    public BookmarkResponse(Long id, ProgramResponse program) {
        this.id = id;
        this.program = program;
    }

    public static BookmarkResponse toResponse(Bookmark bookmark) {
        return BookmarkResponse.builder()
                .id(bookmark.getId())
                .program(ProgramResponse.toResponse(bookmark.getProgram()))
                .build();
    }
}
