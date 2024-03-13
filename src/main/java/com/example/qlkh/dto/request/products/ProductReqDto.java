package com.example.qlkh.dto.request.products;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto extends BaseObjectDto {
    private String code;
    private String name;
    private String namePrint;
    private String note;
    private Long typeId;
    private List<ProductUnitReqDto> addUnits;
    private List<Long> addGroupIds;
    private List<ProductFileDescriptionReqDto> addImages;
    private List<ProductDetailReqDto> addDetails;
    private List<Long> addSupplierIds;

    private List<Long> deleteUnitIds;
    private List<Long> deleteGroupIds;
    private List<Long> deleteImageIds;
    private List<Long> deleteDetailIds;
    private List<Long> deleteSupplierIds;
    private boolean deleted;
}