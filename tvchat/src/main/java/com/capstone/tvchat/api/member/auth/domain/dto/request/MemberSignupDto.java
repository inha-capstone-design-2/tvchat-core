package com.capstone.tvchat.api.member.auth.domain.dto.request;

import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Data
@NoArgsConstructor
public class MemberSignupDto {
    private String email;
    private String password;
    private String nickname;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberSignupDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static Member toMember(MemberSignupDto memberSignupDto, PasswordEncoder passwordEncoder){
        return Member.createBuilder()
                .email(memberSignupDto.getEmail())
                .nickname(memberSignupDto.getNickname())
                .password(passwordEncoder.encode(memberSignupDto.getPassword()))
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email,password);
    }
}
