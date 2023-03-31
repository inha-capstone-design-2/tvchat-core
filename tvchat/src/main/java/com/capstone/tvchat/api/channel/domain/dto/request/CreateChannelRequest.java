package com.capstone.tvchat.api.channel.domain.dto.request;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateChannelRequest {
    private String channelName;

    public static Channel toEntity(CreateChannelRequest createChannelRequest) {
        return Channel.builder()
                .name(createChannelRequest.getChannelName())
                .build();
    }
}
