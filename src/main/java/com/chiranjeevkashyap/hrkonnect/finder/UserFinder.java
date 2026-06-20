package com.chiranjeevkashyap.hrkonnect.finder;

import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFinder {
    private final UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserDetails findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
