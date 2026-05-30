package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.dto.request.RegisterRequest;
import com.chiranjeevkashyap.hrkonnect.dto.request.loginRequest;
import com.chiranjeevkashyap.hrkonnect.dto.response.AuthResponse;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import com.chiranjeevkashyap.hrkonnect.security.AuthUtil;
import com.chiranjeevkashyap.hrkonnect.services.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody loginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}