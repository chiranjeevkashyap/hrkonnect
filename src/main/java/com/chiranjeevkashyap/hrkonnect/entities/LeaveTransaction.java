package com.chiranjeevkashyap.hrkonnect.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_transactions")
@Getter
@Setter
public class LeaveTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private LeaveRequest leaveRequest;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private LeaveBalance leaveBalance;

    private float daysDeducted;

    private String action;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
