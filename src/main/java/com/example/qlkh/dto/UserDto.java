package com.example.qlkh.dto;

import com.example.qlkh.dto.response.RoleRespDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AuditableDto {
    private Long id;
    private String username;
    private String email;
    private Set<RoleRespDto> roles = new HashSet<>();
    private Boolean active;
    private String codeSMS;
    @JsonIgnore
    private String oldPassword;
    @JsonIgnore
    private String password;
    private String confirmPassword;
}
