package com.example.qlkh.service.warehouse;

import com.example.qlkh.dto.request.searchs.*;
import com.example.qlkh.dto.request.warehouse.WarehouseReqDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.projection.*;
import com.example.qlkh.dto.response.warehouse.CardRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WarehouseService {
    List<String> getMapPointHasProductByWarehouseId(@NonNull Long warehouseId);

    List<String> getMapPointHasProductBy(@NonNull StockSearchDto dto);

    List<ProductTypeRespDto> getProductTypeInStock(@NonNull StockSearchDto dto);

    List<ProductRespDto> getProductInStock(@NonNull StockSearchDto dto);

    List<ProductUnitProjectionDto> getUnitInStockBy(@NonNull StockSearchDto dto);

    List<ProductDetailRespDto> getProductDetailInStockBy(@NonNull StockSearchDto dto);

    List<String> getConsignmentInStockBy(@NonNull StockSearchDto dto);

    List<ProductFloorProjectionDto> getFloorDetailBy(@NonNull StockSearchDto dto);

    List<ItemDto> getQuantityInFloor(@NonNull List<ItemDto> itemDtos);

    WarehouseRespDto getById(@NonNull Long id, LocalDateTime modifyDate);

    Map<String, WarehouseLocationRespDto> getLocationMapsByWarehouseId(@NonNull Long id, boolean hasFloorDetails);

    List<ReportProjectionDto> getReport(@NonNull ReportSearchDto dto);

    Page<CardRespDto> getCard(@NonNull CardSearchDto dto);

    Page<ProductFloorDetailProjectionDto> searchProductFloorDetail(@NonNull ProductFloorSearchDto dto);

    Page<WarehouseRespDto> search(@NonNull WarehouseSearchDto dto);

    WarehouseRespDto add(@NonNull WarehouseReqDto dto);

    WarehouseRespDto update(@NonNull Long id, @NonNull WarehouseReqDto dto);

    boolean deleteById(@NonNull Long id);
}
