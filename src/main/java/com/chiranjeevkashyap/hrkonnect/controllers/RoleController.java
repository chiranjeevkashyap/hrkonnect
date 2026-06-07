package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.dto.requests.RoleRequest;
<<<<<<< Updated upstream
import com.chiranjeevkashyap.hrkonnect.services.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
=======
import com.chiranjeevkashyap.hrkonnect.enums.Role;
import com.chiranjeevkashyap.hrkonnect.services.RoleService;
import lombok.RequiredArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
<<<<<<< Updated upstream
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleController {

    RoleService roleService;

    @PostMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeRole(@RequestBody RoleRequest request){
        return roleService.changeRole(request);
=======
public class RoleController {

    private final RoleService roleService;

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable Long id,@RequestBody RoleRequest request){
        roleService.updateRole(id,request);
        return ResponseEntity.ok().build();
>>>>>>> Stashed changes
    }
}
