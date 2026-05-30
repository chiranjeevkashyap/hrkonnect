package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveBalanceMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveBalanceService {
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveBalanceMapper mapper;

    public List<LeaveBalanceDto> getLeaveBalances() {
        return mapper.toDtoList(leaveBalanceRepository.findAll());
    }
}
