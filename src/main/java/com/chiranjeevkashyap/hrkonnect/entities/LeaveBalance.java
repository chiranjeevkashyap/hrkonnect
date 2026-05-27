package com.chiranjeevkashyap.hrkonnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
        name = "leave_balances",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "leave_type_id", "year"})
        }
)
@Setter
@Getter
@NoArgsConstructor
@ToString
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    @JsonIgnore
    private LeaveType leaveType;

    private Integer totalLeaves;

    private Integer usedLeaves;

    private Integer remainingLeaves;

    private Integer year;
}
