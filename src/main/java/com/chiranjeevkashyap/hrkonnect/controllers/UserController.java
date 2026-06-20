package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.query.LeaveBalanceQueryService;
import com.chiranjeevkashyap.hrkonnect.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final LeaveBalanceQueryService leaveBalanceQueryService;

    private final UserQueryService userQueryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userQueryService.getUsers());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userQueryService.getUser(userId));
    }

    @GetMapping("/balances")
    public ResponseEntity<?> getBalances() {
        return ResponseEntity.ok(leaveBalanceQueryService.getBalances());
    }

    @GetMapping("/balances/{typeId}")
    public ResponseEntity<?> getBalance(@PathVariable Long typeId) {
        return ResponseEntity.ok(leaveBalanceQueryService.getBalance(typeId));
    }
}
