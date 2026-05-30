package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.exceptions.BusinessRuleViolationException;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveRequestMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveRequestRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveTypeRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final UserRepository userRepository;
    private final LeaveRequestMapper mapper;

    public List<LeaveRequestDto> getLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        if (leaveRequests.isEmpty()) {
            throw new ResourceNotFoundException("Ready to create first Leave Request");
        }
        return mapper.toDtoList(leaveRequests);
    }

    public LeaveRequestDto getLeaveRequestById(Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        if (leaveRequest.isPresent()) {
            return mapper.toDto(leaveRequest.get());
        }
        throw new ResourceNotFoundException("Leave Request not found with id: " + id);
    }

    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {
        if (leaveRequestRepository.existsByAppliedByIdAndFromDateAndToDate(dto.getAppliedById(), dto.getFromDate(), dto.getToDate())) {
            throw new BusinessRuleViolationException("A leave request already exists for the specified date range.");
        }

        User user = userRepository.findById(dto.getAppliedById()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getAppliedById()));

        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId()).orElseThrow(() -> new ResourceNotFoundException("Leave type not found with id: " + dto.getLeaveTypeId()));

        LeaveRequest entity = mapper.toEntity(dto);

        entity.setAppliedBy(user);
        entity.setLeaveType(leaveType);
        long totalDays = ChronoUnit.DAYS.between(
                dto.getFromDate(),
                dto.getToDate()
        ) + 1;
        entity.setTotalDays(totalDays);

        return mapper.toDto(leaveRequestRepository.save(entity));
    }
}
