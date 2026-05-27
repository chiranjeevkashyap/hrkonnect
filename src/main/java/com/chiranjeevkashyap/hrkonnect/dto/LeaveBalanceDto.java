package com.chiranjeevkashyap.hrkonnect.dto;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveType;
import com.chiranjeevkashyap.hrkonnect.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceDto {
    private Long id;
    @JsonIgnore
    private User user;
    private LeaveType leaveType;
    private Integer totalLeaves;
    private Integer usedLeaves;
    private Integer remainingLeaves;
    private Integer year;
}
