package com.chiranjeevkashyap.hrkonnect.security;

public record JwtUserPrinciple(
        Long userId,
        String email,
        String role
) {
}
