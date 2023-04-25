package com.capstone.tvchat.api.bbs.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyArticleRequest {
    private String title;
    private String content;
}
