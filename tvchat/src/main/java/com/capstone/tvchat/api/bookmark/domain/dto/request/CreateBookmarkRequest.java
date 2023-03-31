package com.capstone.tvchat.api.bookmark.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookmarkRequest {
    private Long memberId;
    private Long programId;
}
