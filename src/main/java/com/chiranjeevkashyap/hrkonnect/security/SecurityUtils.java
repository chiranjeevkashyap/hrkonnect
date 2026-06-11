package com.chiranjeevkashyap.hrkonnect.security;

import com.chiranjeevkashyap.hrkonnect.records.ContextUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public ContextUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authenticated user found.");
        }
        return (ContextUser) authentication.getPrincipal();
    }
}