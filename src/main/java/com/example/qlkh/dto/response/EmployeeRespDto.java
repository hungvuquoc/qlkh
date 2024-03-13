package com.example.qlkh.dto.response;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.GenderType;
import com.example.qlkh.dto.BaseObjectDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeRespDto extends BaseObjectDto {
    private String code;
    private String name;
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDate birthday;
    private GenderType gender;
    private String phone;
    private boolean deleted;

    public String getTag () {
        return String.format("%s - %s", code, name);
    }
}
