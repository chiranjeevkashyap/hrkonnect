package com.chiranjeevkashyap.hrkonnect.records;

public record ContextUser(
        Long userId,
        String email,
        String role
) {
}
