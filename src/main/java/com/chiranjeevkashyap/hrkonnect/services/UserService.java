package com.chiranjeevkashyap.hrkonnect.services;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveBalanceDto;
import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import com.chiranjeevkashyap.hrkonnect.mappers.LeaveBalanceMapper;
import com.chiranjeevkashyap.hrkonnect.mappers.UserMapper;
import com.chiranjeevkashyap.hrkonnect.repositories.LeaveBalanceRepository;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final UserMapper userMapper;
    private final LeaveBalanceMapper leaveBalanceMapper;

    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        }
        throw new ResourceNotFoundException("Employee not found with id: " + id);
    }

    public List<LeaveBalanceDto> getBalances(Long id) {
        isUserExistById(id);
        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findByUserId(id);
        if (leaveBalances.isEmpty()) {
            throw new ResourceNotFoundException("Employee Leave Summary not found with id: " + id);
        }
        return leaveBalanceMapper.toDtoList(leaveBalances);
    }

    public LeaveBalanceDto getBalance(Long id, Long typeId) {
        isUserExistById(id);
        Optional<LeaveBalance> leaveBalance = leaveBalanceRepository.findByUserIdAndLeaveTypeId(id, typeId);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(
                        "User not found with email: " + email
                ));
    }
}
