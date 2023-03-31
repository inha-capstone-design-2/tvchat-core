package com.capstone.tvchat.api.program.domain.dto.request;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import com.capstone.tvchat.api.program.domain.entity.Program;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
public class CreateProgramRequest {
    private String title;
    private Long channelId;


    public static Program toEntity(CreateProgramRequest createProgramRequest, Channel channel) {
        return Program.builder()
                .title(createProgramRequest.getTitle())
                .channel(channel)
                .build();
    }
}
