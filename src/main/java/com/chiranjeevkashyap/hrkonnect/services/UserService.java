package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.finder.UserFinder;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveBalanceMapper;
import com.chiranjeevkashyap.hrkonnect.mappers.UserMapper;
import com.chiranjeevkashyap.hrkonnect.records.ContextUser;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import com.chiranjeevkashyap.hrkonnect.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserFinder userFinder;
    private final UserRepository userRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final UserMapper userMapper;
    private final LeaveBalanceMapper leaveBalanceMapper;
    private final SecurityUtils securityUtils;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No Employee Exist.");
        }
        return userMapper.toDtoList(users);
    }

    public UserDto getUser() {
        ContextUser contextUser = securityUtils.getCurrentUser();
        User user = userFinder.findById(contextUser.userId());
        return userMapper.toDto(user);
    }

    public List<LeaveBalanceDto> getBalances() {
        ContextUser contextUser = securityUtils.getCurrentUser();
        isUserExistById(contextUser.userId());
        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findByUserId(contextUser.userId());
        if (leaveBalances.isEmpty()) {
            throw new ResourceNotFoundException("Employee Leave Summary not found with id: " + contextUser.userId());
        }
        return leaveBalanceMapper.toDtoList(leaveBalances);
    }

    public LeaveBalanceDto getBalance(Long typeId) {
        ContextUser contextUser = securityUtils.getCurrentUser();
        isUserExistById(contextUser.userId());
        Optional<LeaveBalance> leaveBalance = leaveBalanceRepository.findByUserIdAndLeaveTypeId(contextUser.userId(), typeId);
        if (leaveBalance.isPresent()) {
            return leaveBalanceMapper.toDto(leaveBalance.get());
        }
        throw new ResourceNotFoundException("Leave not found with id: " + typeId);
    }


    private void isUserExistById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
    }

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
