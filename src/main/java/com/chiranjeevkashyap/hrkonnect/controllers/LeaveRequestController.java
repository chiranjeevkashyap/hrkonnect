package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.command.LeaveRequestCommandService;
import com.chiranjeevkashyap.hrkonnect.dto.LeaveRequestDto;
import com.chiranjeevkashyap.hrkonnect.query.LeaveRequestQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestCommandService commandService;
    private final LeaveRequestQueryService queryService;

    @GetMapping
    public ResponseEntity<?> getLeaveRequests() {
        List<LeaveRequestDto> leaveRequests = queryService.getLeaveRequests();
        return ResponseEntity.ok(leaveRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveRequest(@PathVariable Long id) {
        LeaveRequestDto leaveRequest = queryService.getLeaveRequestById(id);
        return ResponseEntity.ok(leaveRequest);
    }

    @PostMapping
    public ResponseEntity<?> createLeaveRequest(@RequestBody @Valid LeaveRequestDto leaveRequestDto) {
        LeaveRequestDto leaveRequest = commandService.createLeaveRequest(leaveRequestDto);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelLeaveRequest(@PathVariable Long id) {
        commandService.cancelLeaveRequest(id);
        return ResponseEntity.ok("Leave Request deleted with id: " + id);
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequestDto> approveLeaveRequest(@PathVariable Long id) {
        LeaveRequestDto leaveRequestDto = commandService.approveLeaveRequest(id);
        return ResponseEntity.ok(leaveRequestDto);
    }

    @GetMapping("/reject/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequestDto> rejectLeaveRequest(@PathVariable Long id) {
        LeaveRequestDto leaveRequestDto = commandService.rejectLeaveRequest(id);
        return ResponseEntity.ok(leaveRequestDto);
    }

}
