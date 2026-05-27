package com.chiranjeevkashyap.hrkonnect.repositories;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}