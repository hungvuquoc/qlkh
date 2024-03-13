package com.example.qlkh.service.mapper.warehouse.inventories;

import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryItemRespDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryRespDto;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.inventories.WhInventory;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryEmployeeConnect;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryFileConnect;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryItem;
import com.example.qlkh.service.mapper.EmployeeMapper;
import com.example.qlkh.service.mapper.FileDescriptionMapper;
import com.example.qlkh.service.mapper.products.ProductTypeMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhInventoryMapper {
    WhInventoryMapper INSTANCE = Mappers.getMapper(WhInventoryMapper.class);

    @Mapping(target = "warehouse", expression = "java(mapWarehouse(entity.getWarehouse()))")
    @Mapping(target = "productType", expression = "java(mapProductType(entity.getProductType()))")
    @Mapping(target = "items", expression = "java(mapItems(entity.getItems()))")
    @Mapping(target = "employees", expression = "java(mapEmployees(entity.getEmployees()))")
    @Mapping(target = "files", expression = "java(mapFiles(entity.getFiles()))")
    WhInventoryRespDto mapEntityToDto(WhInventory entity);

    @Mapping(target = "warehouse", expression = "java(mapWarehouse(entity.getWarehouse()))")
    @Mapping(target = "productType", expression = "java(mapProductType(entity.getProductType()))")
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "files", ignore = true)
    WhInventoryRespDto mapEntityToHiddenDto(WhInventory entity);

    default WarehouseRespDto mapWarehouse(Warehouse warehouse) {
        if (warehouse == null) {
            return null;
        }
        return WarehouseMapper.INSTANCE.mapEntityToDtoHidden(warehouse);
    }

    default ProductTypeRespDto mapProductType(ProductType productType) {
        if (productType == null) {
            return null;
        }
        return ProductTypeMapper.INSTANCE.mapEntityToDto(productType);
    }

    default List<WhInventoryItemRespDto> mapItems(List<WhInventoryItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(WhInventoryItemMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    default List<EmployeeRespDto> mapEmployees(List<WhInventoryEmployeeConnect> employeeConnects) {
        if (CollectionUtils.isEmpty(employeeConnects)) {
            return Collections.emptyList();
        }
        return employeeConnects.stream().map(ec -> EmployeeMapper.INSTANCE.mapEntityToDto(ec.getEmployee())).collect(Collectors.toList());
    }

    default List<FileDescriptionDto> mapFiles(List<WhInventoryFileConnect> fileConnects) {
        if (CollectionUtils.isEmpty(fileConnects)) {
            return Collections.emptyList();
        }
        return fileConnects.stream().map(fc -> FileDescriptionMapper.INSTANCE.entityToDto(fc.getFile())).collect(Collectors.toList());
    }
}
