package com.chiranjeevkashyap.hrkonnect.records;

import lombok.Builder;

@Builder
public record AuthResponse (String token) {
}
