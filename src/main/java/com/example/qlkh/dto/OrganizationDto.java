package com.example.qlkh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto implements Serializable {
    private String name;
    private String enterpriseCode;
    private String managerName;
    private String address;
    private String phone;
    private String email;
    private String description;
}
