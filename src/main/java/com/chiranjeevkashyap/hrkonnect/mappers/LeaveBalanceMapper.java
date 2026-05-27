package com.chiranjeevkashyap.hrkonnect.mappers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {
    LeaveBalanceDto toDto(LeaveBalance entity);
    LeaveBalance toEntity(LeaveBalanceDto dto);
    List<LeaveBalanceDto> toDtoList(List<LeaveBalance> entities);
}
