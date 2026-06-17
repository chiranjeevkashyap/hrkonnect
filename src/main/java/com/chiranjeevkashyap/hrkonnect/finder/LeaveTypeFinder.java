package com.chiranjeevkashyap.hrkonnect.finder;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeaveTypeFinder {
    private final LeaveTypeRepository repository;

    public LeaveType findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave type not found with id: " + id));
    }
}
