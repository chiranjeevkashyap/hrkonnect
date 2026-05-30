package com.chiranjeevkashyap.hrkonnect.entities;

import com.chiranjeevkashyap.hrkonnect.enums.Role;
import com.chiranjeevkashyap.hrkonnect.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<LeaveBalance> leaveBalances;

    // Inverse side: mappedBy points to 'appliedBy' in LeaveRequest
    @OneToMany(mappedBy = "appliedBy")
    @ToString.Exclude
    private List<LeaveRequest> appliedByList;

    @OneToMany(mappedBy = "approvedBy")
    @ToString.Exclude
    private List<LeaveRequest> approveByList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

}
