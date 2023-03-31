package com.capstone.tvchat.api.channel.domain.dto.request;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChannelCreateRequest {
    private String channelName;

    public static Channel toEntity(ChannelCreateRequest channelCreateRequest) {
        return Channel.builder()
                .name(channelCreateRequest.getChannelName())
                .build();
    }
}
