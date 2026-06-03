package com.chiranjeevkashyap.hrkonnect.dto.requests;

public record RegisterRequest(
        String name,
        String username,
        String email,
        String password,
        String role
) {
}
