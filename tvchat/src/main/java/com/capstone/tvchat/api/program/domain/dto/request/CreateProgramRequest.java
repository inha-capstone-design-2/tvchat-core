package com.capstone.tvchat.api.program.domain.dto.request;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProgramRequest {
    private String title;
    private Long channelId;

    @Builder
    public CreateProgramRequest(String title, Long channelId) {
        this.title = title;
        this.channelId = channelId;
    }

    public static Program toEntity(CreateProgramRequest createProgramRequest, Channel channel) {
        return Program.builder()
                .title(createProgramRequest.getTitle())
                .channel(channel)
                .build();
    }
}
