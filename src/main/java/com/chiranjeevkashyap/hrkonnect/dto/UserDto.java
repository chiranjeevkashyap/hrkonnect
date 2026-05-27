package com.chiranjeevkashyap.hrkonnect.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
