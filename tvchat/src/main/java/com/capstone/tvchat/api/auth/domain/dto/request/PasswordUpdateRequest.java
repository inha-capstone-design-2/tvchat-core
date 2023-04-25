package com.capstone.tvchat.api.auth.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordUpdateRequest {
    private String email;

    private String password;
}
