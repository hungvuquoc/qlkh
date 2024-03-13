package com.example.qlkh.service.mapper.warehouse.inventories;

import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryItemRespDto;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductDetail;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryItem;
import com.example.qlkh.service.mapper.products.ProductDetailMapper;
import com.example.qlkh.service.mapper.products.ProductMapper;
import com.example.qlkh.service.mapper.products.ProductUnitMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseFloorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WhInventoryItemMapper {
    WhInventoryItemMapper INSTANCE = Mappers.getMapper(WhInventoryItemMapper.class);

    @Mapping(target = "product", expression = "java(mapProduct(entity.getProduct()))")
    @Mapping(target = "unitCheck", expression = "java(mapUnitCheck(entity.getUnitCheck()))")
    @Mapping(target = "productDetail", expression = "java(mapProductDetail(entity.getProductDetail()))")
    @Mapping(target = "floor", expression = "java(mapFloor(entity.getFloor()))")
    WhInventoryItemRespDto entityToDto(WhInventoryItem entity);

    default ProductRespDto mapProduct(Product product) {
        if (product == null) {
            return null;
        }
        return ProductMapper.INSTANCE.mapEntityToDtoHidden(product);
    }

    default ProductUnitRespDto mapUnitCheck(ProductUnitConnect unitConnect) {
        if (unitConnect == null || unitConnect.getUnit() == null) {
            return null;
        }

        return ProductUnitMapper.INSTANCE.mapEntityToDto(unitConnect.getUnit());
    }

    default ProductDetailRespDto mapProductDetail(ProductDetail detail) {
        if (detail == null) {
            return null;
        }
        return ProductDetailMapper.INSTANCE.mapEntityToDto(detail);
    }

    default WarehouseFloorRespDto mapFloor(WarehouseFloor floor) {
        if (floor == null) {
            return null;
        }
        return WarehouseFloorMapper.INSTANCE.mapEntityToDto(floor);
    }
}
