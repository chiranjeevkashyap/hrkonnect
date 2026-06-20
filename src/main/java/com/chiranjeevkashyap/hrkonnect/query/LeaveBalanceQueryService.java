package com.chiranjeevkashyap.hrkonnect.query;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.finder.LeaveBalanceFinder;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveBalanceMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import com.chiranjeevkashyap.hrkonnect.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveBalanceQueryService {
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveBalanceMapper leaveBalanceMapper;
    private final LeaveBalanceFinder leaveBalanceFinder;

    private final UserFinder userFinder;

    private final AuthUtil authUtil;

    public List<LeaveBalanceDto> getBalances() {
        User user = userFinder.findById(authUtil.getCurrentUser().userId());
        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findByUserId(user.getId());
        return leaveBalanceMapper.toDtoList(leaveBalances);
    }

    public LeaveBalanceDto getBalance(Long typeId) {
        User user = userFinder.findById(authUtil.getCurrentUser().userId());
        LeaveBalance leaveBalance = leaveBalanceFinder.findByUserIdAndLeaveTypeId(user.getId(), typeId);
        return leaveBalanceMapper.toDto(leaveBalance);
    }
}
