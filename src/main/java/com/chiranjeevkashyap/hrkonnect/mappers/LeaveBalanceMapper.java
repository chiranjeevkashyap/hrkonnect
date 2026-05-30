package com.chiranjeevkashyap.hrkonnect.mappers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {
    @Mapping(source = "leaveType.id", target = "leaveTypeId")
    @Mapping(source = "leaveType.name", target = "leaveTypeName")
    LeaveBalanceDto toDto(LeaveBalance entity);
    LeaveBalance toEntity(LeaveBalanceDto dto);
    List<LeaveBalanceDto> toDtoList(List<LeaveBalance> entities);
}
