package com.example.qlkh.dto.request.warehouse.imports;

import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.imports.WhImportItem;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhImportItemDetailReqDto implements Serializable {
    private Long id;
    private Long parentId;
    private Long floorId;
    private Double quantity;
}
