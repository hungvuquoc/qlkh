package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.dto.response.products.*;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.service.mapper.FileDescriptionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "groups", qualifiedByName = "mapGroupsToDto")
    @Mapping(target = "units", qualifiedByName = "mapUnitsToDto")
    @Mapping(target = "images", qualifiedByName = "mapImagesToDto")
    @Mapping(target = "details", qualifiedByName = "mapDetailsToDto")
    @Mapping(target = "suppliers", qualifiedByName = "mapSuppliersToDto")
    ProductRespDto mapEntityToDto(Product entity);

    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "units", source = "unitConnects",qualifiedByName = "mapUnitsToDto")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "details", source = "details", qualifiedByName = "mapDetailsToDto")
    @Mapping(target = "suppliers", ignore = true)
    ProductRespDto mapEntityToDto(Product entity, List<ProductUnitConnect> unitConnects, List<ProductDetail> details);

    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "units", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "details", ignore = true)
    @Mapping(target = "suppliers", ignore = true)
    ProductRespDto mapEntityToDtoHidden(Product entity);

    @Named("mapUnitsToDto")
    default List<ProductUnitRespDto> mapUnitsToDto(List<ProductUnitConnect> unitConnects) {
        if (CollectionUtils.isEmpty(unitConnects)) {
            return Collections.emptyList();
        }
        List<ProductUnitRespDto> unitRespDtos = new ArrayList<>();
        for (ProductUnitConnect unitConnect : unitConnects) {
            if (unitConnect.getUnit() == null) {
                continue;
            }

            unitRespDtos.add(this.mapUnitEntityToUnitDto(unitConnect));
        }
        return unitRespDtos;
    }

    default ProductUnitRespDto mapUnitEntityToUnitDto(ProductUnitConnect unitConnect) {
        ProductUnit unit = unitConnect.getUnit();
        ProductUnitRespDto unitRespDto = new ProductUnitRespDto();
        unitRespDto.setId(unitConnect.getId());
        unitRespDto.setCode(unit.getCode());
        unitRespDto.setName(unit.getName());
        unitRespDto.setUnitId(unit.getId());
        unitRespDto.setRatio(unitConnect.getRatio());
        unitRespDto.setDefault(unitConnect.isDefault());
        unitRespDto.setUseReport(unitConnect.isUseReport());
        unitRespDto.setDeleted(unitConnect.isDeleted());

        return unitRespDto;
    }

    @Named("mapGroupsToDto")
    default List<ProductGroupRespDto> mapGroupsToDto(List<ProductGroupConnect> groupConnects) {
        if (CollectionUtils.isEmpty(groupConnects)) {
            return Collections.emptyList();
        }
        return groupConnects.stream()
                .map(groupConnect -> ProductGroupMapper.INSTANCE.mapEntityToDto(groupConnect.getGroup()))
                .collect(Collectors.toList());
    }

    @Named("mapImagesToDto")
    default List<FileDescriptionDto> mapImagesToDto(List<ProductFileDescriptionConnect> imageConnects) {
        if (CollectionUtils.isEmpty(imageConnects)) {
            return Collections.emptyList();
        }

        List<FileDescriptionDto> imageRespDtos = new ArrayList<>();
        for (ProductFileDescriptionConnect imageConnect : imageConnects) {
            FileDescriptionDto imageRespDto = FileDescriptionMapper.INSTANCE.entityToDto(imageConnect.getFile());
            imageRespDto.setOrderBy(imageConnect.getOrderBy());
            imageRespDtos.add(imageRespDto);
        }

        return imageRespDtos;
    }

    @Named("mapDetailsToDto")
    default List<ProductDetailRespDto> mapDetailsToDto(List<ProductDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            return Collections.emptyList();
        }

        List<ProductDetailRespDto> detailRespDtos = new ArrayList<>();
        for (ProductDetail detail : details) {
            ProductDetailRespDto detailRespDto = new ProductDetailRespDto();
            detailRespDto.setId(detail.getId());
            detailRespDto.setQuality(detail.getQuality());
            detailRespDto.setSize(detail.getSize());
            detailRespDto.setPrice(detail.getPrice());
            detailRespDtos.add(detailRespDto);
        }
        return detailRespDtos;
    }

    @Named("mapSuppliersToDto")
    default List<SupplierRespDto> mapSuppliersToDto(List<ProductSupplierConnect> supplierConnects) {
        if (CollectionUtils.isEmpty(supplierConnects)) {
            return Collections.emptyList();
        }
        return supplierConnects.stream()
                .map(supplierConnect -> SupplierMapper.INSTANCE.mapEntityToDto(supplierConnect.getSupplier()))
                .collect(Collectors.toList());
    }
}
