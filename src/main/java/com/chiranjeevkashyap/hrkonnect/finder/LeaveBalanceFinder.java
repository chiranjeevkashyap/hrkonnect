package com.chiranjeevkashyap.hrkonnect.finder;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveBalanceFinder {
    private final LeaveBalanceRepository repository;

    public LeaveBalance findByUserIdAndLeaveTypeId(Long id, Long typeId) {
        return repository.findByUserIdAndLeaveTypeId(id, typeId).orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + typeId));
    }
}
