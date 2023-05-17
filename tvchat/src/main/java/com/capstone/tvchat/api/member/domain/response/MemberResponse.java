package com.capstone.tvchat.api.member.domain.response;

import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private String email;
    private String nickname;

    @Builder
    public MemberResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static MemberResponse toResponse(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
