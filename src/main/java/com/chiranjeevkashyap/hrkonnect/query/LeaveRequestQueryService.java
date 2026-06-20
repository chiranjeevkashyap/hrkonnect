package com.chiranjeevkashyap.hrkonnect.query;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveRequest;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.finder.LeaveRequestFinder;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveRequestMapper;

import com.chiranjeevkashyap.hrkonnect.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestQueryService {
    private final LeaveRequestFinder leaveRequestFinder;
    private final LeaveRequestMapper mapper;

    private final UserFinder userFinder;
    private final AuthUtil authUtil;

    public List<LeaveRequestDto> getLeaveRequests() {
        User user = getCurrentUser();
        List<LeaveRequest> leaveRequests = leaveRequestFinder.findByAppliedById(user.getId());
        return mapper.toDtoList(leaveRequests);
    }

    public LeaveRequestDto getLeaveRequestById(Long id) {
        User user = getCurrentUser();
        LeaveRequest leaveRequest = leaveRequestFinder.findByAppliedByIdAndId(user.getId(), id);
        return mapper.toDto(leaveRequest);
    }

    private User getCurrentUser() {
        return userFinder.findById(authUtil.getCurrentUser().userId());
    }
}
