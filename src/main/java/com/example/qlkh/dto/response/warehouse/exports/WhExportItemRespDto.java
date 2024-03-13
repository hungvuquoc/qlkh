package com.example.qlkh.dto.response.warehouse.exports;

import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
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
public class WhExportItemRespDto implements Serializable {
    private Long id;
    private ProductRespDto product;
    private ProductUnitRespDto unitTarget;
    private ProductUnitRespDto unitSource;
    private ProductDetailRespDto productDetail;
    private String consignmentNumber;
    private Double quantityTarget;
    private Double quantitySource;
    private List<WhExportItemDetailRespDto> details;
}
