package com.example.qlkh.dto.response.warehouse.imports;

import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhImportItemDetailRespDto implements Serializable {
    private Long id;
    private WarehouseFloorRespDto floor;
    private Double quantity;
    private String mapPoint;
    private WarehouseLocationRespDto location;
}
