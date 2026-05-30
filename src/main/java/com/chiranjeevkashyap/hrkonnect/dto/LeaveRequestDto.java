package com.chiranjeevkashyap.hrkonnect.dto;

import com.chiranjeevkashyap.hrkonnect.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
// Hide Null Values (Optional)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "appliedById",
        "leaveTypeId",
        "fromDate",
        "toDate",
        "totalDays",
        "reason",
        "status",
        "appliedAt",
        "approvedById",
        "approvedAt",
        "remarks"
})
public class LeaveRequestDto {
    private Long id;

    @NotNull(message = "Applied By Id is required")
    private Long appliedById;

    @NotNull(message = "Leave Type Id is required")
    private Long leaveTypeId;

    @NotNull(message = "From date is required")
    @FutureOrPresent(message = "From date cannot be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    @FutureOrPresent(message = "To date cannot be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private float totalDays;

    @NotBlank(message = "Reason is required")
    @Size(min = 5, max = 500)
    private String reason;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LeaveStatus status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime appliedAt;

    private Long approvedById;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime approvedAt;

    @Size(max = 500)
    private String remarks;
}
