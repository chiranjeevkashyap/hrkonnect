package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.records.RoleRequest;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.mappers.UserMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleService {
    UserFinder finder;
    UserRepository repository;
    UserMapper mapper;

    public UserDto changeRole(RoleRequest request) {
        User user = finder.findById(request.id());
        user.setRole(request.role());
        return mapper.toDto(repository.save(user));
    }
}
