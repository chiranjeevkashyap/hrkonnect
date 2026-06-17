package com.chiranjeevkashyap.hrkonnect.repositories;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByAppliedById(Long appliedBy);

    Optional<LeaveRequest> findByAppliedByIdAndId(Long appliedById, Long requestId);

    boolean existsByAppliedByIdAndFromDateAndToDate(Long appliedById, LocalDate fromDate, LocalDate toDate);
}