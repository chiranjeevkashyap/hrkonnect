package com.chiranjeevkashyap.hrkonnect.records;

import com.chiranjeevkashyap.hrkonnect.enums.Role;
import jakarta.validation.constraints.NotNull;

public record RoleRequest(@NotNull Long id, @NotNull Role role) {
}
