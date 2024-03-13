package com.example.qlkh.dto.request.warehouse.imports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhImportItemReqDto implements Serializable {
    private Long id;
    private Long parentId;
    private Long productId;
    private Long unitTargetId;
    private Long unitSourceId;
    private Long productDetailId;
    private String consignmentNumber;
    private Double quantityTarget;
    private Double quantitySource;
    private List<WhImportItemDetailReqDto> details;
}
