package com.chiranjeevkashyap.hrkonnect.repositories;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    List<LeaveBalance> findByUserId(Long userId);
    Optional<LeaveBalance> findByUserIdAndLeaveTypeId(Long id, Long typeId);
}
