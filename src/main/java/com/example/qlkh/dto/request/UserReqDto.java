package com.example.qlkh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto implements Serializable {
    private Long id;
    private Long employeeId;
    private String email;
    private Set<String> roles;
    private Set<String> authorities;
    private boolean active;
    private String oldPassword;
    private String password;
    private String confirmPassword;
    private boolean genCode;
}
