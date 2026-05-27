package com.chiranjeevkashyap.hrkonnect.dto;

import com.chiranjeevkashyap.hrkonnect.entities.LeaveBalance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeDto {
    private Long id;
    private String name;
    private Integer defaultDays;
    private List<LeaveBalance> leaveBalances;
}
