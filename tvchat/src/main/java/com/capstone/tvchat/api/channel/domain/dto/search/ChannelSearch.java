package com.capstone.tvchat.api.channel.domain.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChannelSearch {
    private Long channelId;
    private String channelName;
}
