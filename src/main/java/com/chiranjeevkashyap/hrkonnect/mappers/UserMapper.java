package com.chiranjeevkashyap.hrkonnect.mappers;

import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toDtoList(List<User> users);
}
