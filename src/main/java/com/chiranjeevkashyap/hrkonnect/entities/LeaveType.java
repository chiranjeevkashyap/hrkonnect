package com.chiranjeevkashyap.hrkonnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leave_types")
@Setter
@Getter
@NoArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer defaultDays;

    @OneToMany(mappedBy = "leaveType")
    @JsonIgnore
    private List<LeaveBalance> leaveBalances = new ArrayList<>();

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRequest> leaveRequest;
}
