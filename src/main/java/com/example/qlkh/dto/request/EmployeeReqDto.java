package com.example.qlkh.dto.request;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.GenderType;
import com.example.qlkh.dto.BaseObjectDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeReqDto extends BaseObjectDto {
    private String code;
    private String name;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDate birthday;
    private GenderType gender;
    private String phone;
    private boolean deleted;
}
