package com.chiranjeevkashyap.hrkonnect.repositories;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}