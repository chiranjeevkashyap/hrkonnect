package com.chiranjeevkashyap.hrkonnect.dto;

import com.chiranjeevkashyap.hrkonnect.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeaveRequestDto {
    private Long id;

    @NotNull(message = "Applied By is required")
    private Long appliedById;

    @NotNull(message = "Leave Type is required")
    private Long leaveTypeId;

    @NotNull(message = "From date is required")
    @FutureOrPresent(message = "From date cannot be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    @FutureOrPresent(message = "To date cannot be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;

    private float totalDays;

    @NotBlank(message = "Reason is required")
    @Size(min = 5, max = 500)
    private String reason;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LeaveStatus status;

    private LocalDateTime appliedAt;

    private Long approvedById;

    private LocalDateTime approvedAt;

    @Size(max = 500)
    private String remarks;
}
