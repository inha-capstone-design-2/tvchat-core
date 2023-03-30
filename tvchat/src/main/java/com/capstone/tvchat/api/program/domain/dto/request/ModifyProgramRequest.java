package com.capstone.tvchat.api.program.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyProgramRequest {
    private String title;
    private Long channelId;
}
