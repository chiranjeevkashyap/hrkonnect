package com.chiranjeevkashyap.hrkonnect.command;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.enums.LeaveStatus;
import com.chiranjeevkashyap.hrkonnect.exceptions.BusinessRuleViolationException;
import com.chiranjeevkashyap.hrkonnect.finder.LeaveRequestFinder;
import com.chiranjeevkashyap.hrkonnect.finder.LeaveTypeFinder;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveRequestMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveRequestRepository;
import com.chiranjeevkashyap.hrkonnect.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class LeaveRequestCommandService {
    private final LeaveRequestFinder leaveRequestFinder;
    private final LeaveRequestRepository repository;
    private final LeaveRequestMapper mapper;

    private final LeaveTypeFinder leaveTypeFinder;

    private final UserFinder userFinder;
    private final SecurityUtils securityUtils;

    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {
        User user = getCurrentUser();

        if (repository.existsByAppliedByIdAndFromDateAndToDate(user.getId(), dto.getFromDate(), dto.getToDate())) {
            throw new BusinessRuleViolationException("A leave request already exists for the specified date range.");
        }

        LeaveRequest entity = mapper.toEntity(dto);

        entity.setAppliedBy(user);
        entity.setLeaveType(leaveTypeFinder.findById(dto.getLeaveTypeId()));
        long totalDays = ChronoUnit.DAYS.between(
                dto.getFromDate(),
                dto.getToDate()
        ) + 1;
        entity.setTotalDays(totalDays);

        LeaveRequest savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }

    public LeaveRequestDto approveLeaveRequest(Long id) {
        return modifyLeaveRequest(id, LeaveStatus.APPROVED);
    }

    public LeaveRequestDto rejectLeaveRequest(Long id) {
        return modifyLeaveRequest(id, LeaveStatus.REJECTED);
    }

    private LeaveRequestDto modifyLeaveRequest(Long id, LeaveStatus leaveStatus) {
        User user = getCurrentUser();

        LeaveRequest leaveRequest = leaveRequestFinder.findById(id);

        leaveRequest.setApprovedAt(LocalDateTime.now());
        leaveRequest.setApprovedBy(user);
        leaveRequest.setStatus(leaveStatus);

        LeaveRequest savedEntity = repository.save(leaveRequest);

        return mapper.toDto(savedEntity);
    }

    public void cancelLeaveRequest(Long id) {
        leaveRequestFinder.findById(id);
        repository.deleteById(id);
    }

    private User getCurrentUser() {
        return userFinder.findById(securityUtils.getCurrentUser().userId());
    }
}

