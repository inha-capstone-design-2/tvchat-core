package com.capstone.tvchat.common.util;

import com.capstone.tvchat.api.member.domain.enumerate.Authority;
import com.capstone.tvchat.common.exception.CustomAuthException;
import com.capstone.tvchat.common.exception.code.AuthErrorCode;
import com.capstone.tvchat.common.result.JsonResultData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.SECURITY_CONTEXT_NOT_FOUND.getCode())
                    .errorMessage(AuthErrorCode.SECURITY_CONTEXT_NOT_FOUND.getMessage())
                    .build());
        }
        return Long.parseLong(authentication.getName());
    }

    public static Authority checkAuthority() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            return Authority.ROLE_USER;
        } else return Authority.ROLE_ADMIN;
    }
}
