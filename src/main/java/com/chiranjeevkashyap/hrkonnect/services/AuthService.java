package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.request.RegisterRequest;
import com.chiranjeevkashyap.hrkonnect.dto.request.loginRequest;
import com.chiranjeevkashyap.hrkonnect.dto.response.AuthResponse;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.enums.Role;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import com.chiranjeevkashyap.hrkonnect.security.AuthUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthService {

    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    AuthUtil jwtService;

    public AuthResponse register(RegisterRequest request){
        User user = new User();
        user.setName(request.name());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);

        String token = jwtService.generateJwtToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(loginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        User user = (User) authenticate.getPrincipal();

        String token = jwtService.generateJwtToken(user);
        return new AuthResponse(token);
    }
}
