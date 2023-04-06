package com.capstone.tvchat.api.auth.domain.dto.MemberResponseDto;

import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String email;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public MemberResponseDto(String email) {
        this.email = email;
    }

    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.of()
                .email(member.getEmail())
                .build();
    }
}
