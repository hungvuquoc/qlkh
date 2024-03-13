package com.example.qlkh.dto.response.products;

import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.FileDescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRespDto extends BaseObjectDto {
    private String code;
    private String name;
    private String namePrint;
    private String note;
    private ProductTypeRespDto type;
    private List<ProductUnitRespDto> units;
    private List<ProductGroupRespDto> groups;
    private List<FileDescriptionDto> images;
    private List<ProductDetailRespDto> details;
    private List<SupplierRespDto> suppliers;
    private boolean deleted;
}
