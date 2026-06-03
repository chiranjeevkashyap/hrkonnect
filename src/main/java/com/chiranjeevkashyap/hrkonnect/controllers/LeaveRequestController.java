package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.services.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelLeaveRequest(@PathVariable Long id){
        leaveRequestService.cancelLeaveRequest(id);
        return ResponseEntity.ok("Leave Request deleted with id: " + id);
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequestDto> approveLeaveRequest(@PathVariable Long id){
        return ResponseEntity.ok(leaveRequestService.approveLeaveRequest(id));
    }

    @GetMapping("/reject/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequestDto> rejectLeaveRequest(@PathVariable Long id){
        return ResponseEntity.ok(leaveRequestService.rejectLeaveRequest(id));
    }

}
