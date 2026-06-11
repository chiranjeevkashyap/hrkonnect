package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.enums.LeaveStatus;
import com.chiranjeevkashyap.hrkonnect.exceptions.BusinessRuleViolationException;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveRequestMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveRequestRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveTypeRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import com.chiranjeevkashyap.hrkonnect.records.ContextUser;
import com.chiranjeevkashyap.hrkonnect.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final UserRepository userRepository;
    private final LeaveRequestMapper mapper;
    private final SecurityUtils securityUtils;

    public List<LeaveRequestDto> getLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByAppliedById(getCurrentUser().getId());
        if (leaveRequests.isEmpty()) {
            throw new ResourceNotFoundException("Ready to create your first Leave Request");
        }
        return mapper.toDtoList(leaveRequests);
    }

    public LeaveRequestDto getLeaveRequestById(Long id) {
        return mapper.toDto(leaveRequestRepository.findByAppliedByIdAndId(getCurrentUser().getId(), id).orElseThrow(
                () -> new ResourceNotFoundException("Leave Request not found with id: " + id)
        ));
    }

    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {
        User user = getCurrentUser();
        if (leaveRequestRepository.existsByAppliedByIdAndFromDateAndToDate(user.getId(), dto.getFromDate(), dto.getToDate())) {
            throw new BusinessRuleViolationException("A leave request already exists for the specified date range.");
        }

        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("Leave type not found with id: " + dto.getLeaveTypeId())
        );

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

    public void cancelLeaveRequest(Long id) {
        if (leaveRequestRepository.existsById(id)) {
            leaveRequestRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("Leave Request not found with id: " + id);
    }

    public LeaveRequestDto approveLeaveRequest(Long id) {
        return modifyLeaveRequest(id, LeaveStatus.APPROVED);
    }

    public LeaveRequestDto rejectLeaveRequest(Long id) {
        return modifyLeaveRequest(id, LeaveStatus.REJECTED);
    }

    private LeaveRequestDto modifyLeaveRequest(Long id, LeaveStatus leaveStatus) {
        User user = getCurrentUser();
        LeaveRequest leaveRequest = getLeaveRequest(id);
        leaveRequest.setApprovedAt(LocalDateTime.now());
        leaveRequest.setApprovedBy(user);
        leaveRequest.setStatus(leaveStatus);
        return mapper.toDto(leaveRequestRepository.save(leaveRequest));
    }

    private LeaveRequest getLeaveRequest(Long id) {
        return leaveRequestRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Leave Request not found with id: " + id)
        );
    }

    private User getCurrentUser() {
        ContextUser contextUser = securityUtils.getCurrentUser();
        return userRepository.findById(contextUser.userId()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + contextUser.userId())
        );
    }
}
