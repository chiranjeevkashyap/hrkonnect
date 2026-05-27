package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveRequestMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveRequestRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveTypeRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
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

    public LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto) {
        Optional<User> optionalUser = userRepository.findById(leaveRequestDto.getAppliedById());
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + leaveRequestDto.getAppliedById());
        }
        Optional<LeaveBalance> optionalLeaveBalance = leaveBalanceRepository.findById(leaveRequestDto.getLeaveTypeId());
        if (optionalLeaveBalance.isEmpty()) {
            throw new ResourceNotFoundException("Leave Balance not found with id: " + leaveRequestDto.getLeaveTypeId());
        }
        return mapper.toDto(leaveRequestRepository.save(mapper.toEntity(leaveRequestDto)));
    }
}
