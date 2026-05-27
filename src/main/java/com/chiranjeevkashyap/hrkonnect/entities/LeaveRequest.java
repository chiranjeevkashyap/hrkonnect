package com.chiranjeevkashyap.hrkonnect.entities;

import com.chiranjeevkashyap.hrkonnect.enums.LeaveStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
@Getter
@Setter
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owning side: controls the foreign key column 'user_id'
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private User appliedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    @NotNull(message = "Leave type is required")
    private LeaveType leaveType;

    @NotNull(message = "From date is required")
    @FutureOrPresent(message = "From date cannot be in the past")
    @Column(nullable = false)
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    @Column(nullable = false)
    private LocalDate toDate;

    @Positive(message = "Total days must be greater than 0")
    @Column(nullable = false)
    private float totalDays;

    @NotBlank(message = "Reason is required")
    @Size(min = 5, max = 500, message = "Reason must be between 5 and 500 characters")
    @Column(nullable = false, length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime appliedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User approvedBy;

    @Column()
    private LocalDateTime approvedAt;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    @Column(length = 500)
    private String remarks;

    @PrePersist
    public void prePersist() {
        this.appliedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = LeaveStatus.PENDING;
        }
    }

    @AssertTrue(message = "To date must be greater than or equal to from date")
    public boolean isDateRangeValid() {
        if (fromDate == null || toDate == null) {
            return true;
        }
        return !toDate.isBefore(fromDate);
    }
}
