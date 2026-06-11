package com.chiranjeevkashyap.hrkonnect.records;

public record JwtUserPrinciple(
        Long userId,
        String email,
        String role
) {
}
