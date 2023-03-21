package com.capstone.tvchat.api.auth.domain.dto.MemberSignupDto;

import com.capstone.tvchat.api.member.domain.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@NoArgsConstructor
public class MemberSignupDto {
    private String email;
    private String password;
    private String nickname;

    public MemberSignupDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
