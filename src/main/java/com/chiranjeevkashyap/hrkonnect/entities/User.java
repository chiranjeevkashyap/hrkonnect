package com.chiranjeevkashyap.hrkonnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<LeaveBalance> leaveBalances;

    // Inverse side: mappedBy points to 'appliedBy' in LeaveRequest
    @OneToMany(mappedBy = "appliedBy")
    private List<LeaveRequest> appliedByList;

    @OneToMany(mappedBy = "approvedBy")
    private List<LeaveRequest> approveByList;
}
