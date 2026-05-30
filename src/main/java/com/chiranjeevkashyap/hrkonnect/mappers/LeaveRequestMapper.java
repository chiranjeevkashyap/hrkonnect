package com.chiranjeevkashyap.hrkonnect.mappers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {
    @Mapping(source = "appliedBy.id", target = "appliedById")
    @Mapping(source = "leaveType.id", target = "leaveTypeId")
    @Mapping(source = "approvedBy.id", target = "approvedById")
    LeaveRequestDto toDto(LeaveRequest entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appliedBy", ignore = true)
    @Mapping(target = "leaveType", ignore = true)
    @Mapping(target = "approvedBy", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "appliedAt", ignore = true)
    @Mapping(target = "approvedAt", ignore = true)
    LeaveRequest toEntity(LeaveRequestDto dto);
    List<LeaveRequestDto> toDtoList(List<LeaveRequest> entities);
}
