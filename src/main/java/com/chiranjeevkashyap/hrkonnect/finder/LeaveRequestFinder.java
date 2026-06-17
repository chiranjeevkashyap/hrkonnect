package com.chiranjeevkashyap.hrkonnect.finder;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestFinder {
    private final LeaveRequestRepository repository;


    public LeaveRequest findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave Request not found with id: " + id));
    }

    public List<LeaveRequest> findByAppliedById(Long id) {
        List<LeaveRequest> leaveRequests = repository.findByAppliedById(id);
        if (leaveRequests.isEmpty()) {
            throw new ResourceNotFoundException("User has not submitted any leave requests.");
        }
        return leaveRequests;
    }

    public LeaveRequest findByAppliedByIdAndId(Long appliedById, Long requestId) {
        return repository.findByAppliedByIdAndId(appliedById, requestId).orElseThrow(
                () -> new ResourceNotFoundException("Leave Request not found with id: " + requestId)
        );
    }
}
