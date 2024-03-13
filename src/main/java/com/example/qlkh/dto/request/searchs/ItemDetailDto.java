package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.DateConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDetailDto {
    private Long floorId;
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private Double quantity;
}
