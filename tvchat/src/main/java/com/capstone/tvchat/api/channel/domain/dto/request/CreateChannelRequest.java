package com.capstone.tvchat.api.channel.domain.dto.request;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateChannelRequest {
    private String channelName;
    private String description;

    public static Channel toEntity(CreateChannelRequest createChannelRequest) {
        return Channel.createBuilder()
                .name(createChannelRequest.getChannelName())
                .description(createChannelRequest.getDescription())
                .build();
    }
}
