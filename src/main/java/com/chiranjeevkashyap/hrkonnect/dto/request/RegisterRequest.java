package com.chiranjeevkashyap.hrkonnect.dto.request;

public record RegisterRequest(
        String name,
        String username,
        String email,
        String password,
        String role
) {
}
