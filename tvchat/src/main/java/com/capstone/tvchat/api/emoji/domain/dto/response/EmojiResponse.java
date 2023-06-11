package com.capstone.tvchat.api.emoji.domain.dto.response;

import com.capstone.tvchat.api.emoji.domain.entity.Emoji;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmojiResponse {
    private Long id;
    private Long programId;
    private String url;

    @Builder
    public EmojiResponse(Long id, Long programId, String url) {
        this.id = id;
        this.programId = programId;
        this.url = url;
    }

    public static EmojiResponse toResponse(Emoji emoji) {
        return EmojiResponse.builder()
                .id(emoji.getId())
                .programId(emoji.getProgram().getId())
                .url(emoji.getUrl())
                .build();
    }
}
