package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.requests.RoleRequest;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
<<<<<<< Updated upstream
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import lombok.RequiredArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
<<<<<<< Updated upstream
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleService {

    UserRepository userRepository;

    public ResponseEntity<?> changeRole(RoleRequest request) {
        User user = userRepository.findById(request.id()).orElseThrow(() ->
                new UsernameNotFoundException("USER NOT FOUND"));
        user.setRole(request.role());
        userRepository.save(user);
        return ResponseEntity.accepted().body(user);
=======
public class RoleService {

    private final UserRepository userRepository;

    public void updateRole(Long id, RoleRequest role) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        user.setRole(role.getRole());
        userRepository.save(user);
>>>>>>> Stashed changes
    }
}
