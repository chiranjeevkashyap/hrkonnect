package com.chiranjeevkashyap.hrkonnect.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record loginRequest(
        @NotBlank(message = "email is required")
        String email,

        @NotBlank(message = "password is required")
        String password
) {
}
