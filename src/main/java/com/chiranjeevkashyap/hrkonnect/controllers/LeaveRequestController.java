package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.services.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @GetMapping
    public ResponseEntity<?> getLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @PostMapping
    public ResponseEntity<?> createLeaveRequest(@RequestBody @Valid LeaveRequestDto leaveRequestDto) {
        return new ResponseEntity<>(leaveRequestService.createLeaveRequest(leaveRequestDto), HttpStatus.CREATED);
    }
}
