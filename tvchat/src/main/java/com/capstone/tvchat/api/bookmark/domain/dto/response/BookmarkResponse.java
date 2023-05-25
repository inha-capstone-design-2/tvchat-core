package com.capstone.tvchat.api.bookmark.domain.dto.response;

import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookmarkResponse {
    private Long id;
    private Long programId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    @Builder
    public BookmarkResponse(Long id, Long programId, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.programId = programId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BookmarkResponse toResponse(Bookmark bookmark) {
        return BookmarkResponse.builder()
                .id(bookmark.getId())
                .programId(bookmark.getProgram().getId())
                .createdTime(bookmark.getCreatedDate())
                .updatedTime(bookmark.getLastModifiedDate())
                .build();
    }
}
