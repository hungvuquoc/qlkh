package com.example.qlkh.dto.response.warehouse.transfers;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhItemDetailType;
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
public class WhTransferItemDetailRespDto implements Serializable {
    private Long id;
    private WarehouseFloorRespDto floor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private Double quantity;
    private String mapPoint;
    private WhItemDetailType type;
}
