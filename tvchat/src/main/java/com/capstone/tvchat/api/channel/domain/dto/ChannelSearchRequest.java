package com.capstone.tvchat.api.channel.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChannelSearchRequest {
    private Long channelId;
    private String channelName;
}
