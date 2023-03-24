package com.capstone.tvchat.api.bbs.domain.dto.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSearch {
    private String name;
    private Long programId;
    private Integer limit;
    private Integer offset;
    private String orderBy;
    private Sort.Direction direction;

    @Builder
    public BoardSearch(String name, Long programId, Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        this.name = name;
        this.programId = programId;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
        this.direction = direction;
    }
}
