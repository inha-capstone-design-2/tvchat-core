package com.capstone.tvchat.api.auth.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@NoArgsConstructor
public class MemberLoginDto {

    private String email;

    private String password;


    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
