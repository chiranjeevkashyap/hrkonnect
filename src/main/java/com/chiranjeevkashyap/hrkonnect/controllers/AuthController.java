package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.dto.requests.RegisterRequest;
import com.chiranjeevkashyap.hrkonnect.dto.requests.loginRequest;
import com.chiranjeevkashyap.hrkonnect.dto.responses.AuthResponse;
import com.chiranjeevkashyap.hrkonnect.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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