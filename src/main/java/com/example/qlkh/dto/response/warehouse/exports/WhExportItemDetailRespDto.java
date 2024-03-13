package com.example.qlkh.dto.response.warehouse.exports;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhExportItemDetailRespDto implements Serializable {
    private Long id;
    private WarehouseFloorRespDto floor;
    private Double quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String mapPoint;
}
