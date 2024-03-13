package com.example.qlkh.dto.response.projection;

public interface UserProjectionDto {
    Long getId();

    String getEmployeeCode();
    String getEmployeeName();

    String getUsername();

    String getEmail();

    Boolean getActive();
}
