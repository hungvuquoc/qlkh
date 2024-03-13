package com.example.qlkh.dto.response;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDto extends BaseObjectDto {
    private EmployeeRespDto employee;
    private String username;
    private String email;
    private Set<String> roles;
    private Set<String> authorities;
    private Boolean active;
}
