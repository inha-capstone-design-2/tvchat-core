package com.capstone.tvchat.api.program.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProgramSearch {
    private String title;
    private Long channel;
}
