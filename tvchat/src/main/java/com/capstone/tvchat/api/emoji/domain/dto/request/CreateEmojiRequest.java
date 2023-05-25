package com.capstone.tvchat.api.emoji.domain.dto.request;

import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEmojiRequest {
    private Long programId;
    private Program program;
    private String url;
}
