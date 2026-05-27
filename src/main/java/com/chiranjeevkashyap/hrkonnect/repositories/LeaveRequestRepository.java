package com.chiranjeevkashyap.hrkonnect.repositories;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}