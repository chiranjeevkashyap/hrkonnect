package com.chiranjeevkashyap.hrkonnect.security;

import com.chiranjeevkashyap.hrkonnect.enums.Role;

public record JwtUserPrinciple(
        Long userId,
        String email,
        String role
) {
}
