package com.chiranjeevkashyap.hrkonnect.query;

import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.mappers.UserMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserDetailsService {
    private final UserFinder userFinder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUser(Long id) {
        User user = userFinder.findById(id);
        return userMapper.toDto(user);
    }

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String email) {
        return userFinder.findByEmail(email);
    }
}
