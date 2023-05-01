package com.capstone.tvchat.api.channel.domain.dto.response;

import com.capstone.tvchat.api.channel.domain.entity.Channel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChannelResponse {
    private Long channelId;
    private String channelName;
    private LocalDateTime createdTime;
    private Long createdBy;
    private LocalDateTime updatedTime;
    private Long updatedBy;

    @Builder
    public ChannelResponse(Long channelId, String channelName, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
    }

    public static ChannelResponse toResponse(Channel channel) {
        return builder()
                .channelId(channel.getId())
                .channelName(channel.getName())
                .createdTime(channel.getCreatedDate())
                .createdBy(channel.getCreatedBy())
                .updatedTime(channel.getLastModifiedDate())
                .updatedBy(channel.getUpdatedBy())
                .build();
    }
}
