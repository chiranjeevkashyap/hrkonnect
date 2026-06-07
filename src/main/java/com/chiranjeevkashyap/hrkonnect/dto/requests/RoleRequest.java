package com.chiranjeevkashyap.hrkonnect.dto.requests;

import com.chiranjeevkashyap.hrkonnect.enums.Role;
<<<<<<< Updated upstream
import jakarta.validation.constraints.NotNull;

public record RoleRequest(

        @NotNull
        Long id,
        @NotNull
        Role role
) {
=======
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    Role role;
>>>>>>> Stashed changes
}
