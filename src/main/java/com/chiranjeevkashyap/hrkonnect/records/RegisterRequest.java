package com.chiranjeevkashyap.hrkonnect.records;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "username is required")
        String username,

        @NotBlank(message = "email is required")
        String email,

        @NotBlank(message = "password is required")
        String password,

        @NotBlank(message = "role is required")
        String role
) {
}
