package com.chiranjeevkashyap.hrkonnect.mappers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {
    LeaveRequestDto toDto(LeaveRequest entity);
    LeaveRequest toEntity(LeaveRequestDto dto);
    List<LeaveRequestDto> toDtoList(List<LeaveRequest> entities);
}
