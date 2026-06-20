package com.chiranjeevkashyap.hrkonnect.provider;

import com.chiranjeevkashyap.hrkonnect.records.ContextUser;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    public ContextUser getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (!(authentication.getPrincipal()
                instanceof ContextUser contextUser)) {

            throw new AuthenticationCredentialsNotFoundException(
                    "No authenticated user found.");
        }

        return contextUser;
    }
}