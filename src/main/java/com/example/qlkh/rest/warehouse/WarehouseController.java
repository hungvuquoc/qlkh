package com.example.qlkh.rest.warehouse;

import com.example.qlkh.dto.request.searchs.*;
import com.example.qlkh.dto.request.warehouse.WarehouseReqDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.projection.ProductFloorDetailProjectionDto;
import com.example.qlkh.dto.response.projection.ProductFloorProjectionDto;
import com.example.qlkh.dto.response.projection.ProductUnitProjectionDto;
import com.example.qlkh.dto.response.projection.ReportProjectionDto;
import com.example.qlkh.dto.response.warehouse.CardRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.service.warehouse.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/{warehouseId}/map-points/has-product")
    public List<String> getMapPointHasProductByWarehouseId(@PathVariable Long warehouseId) {
        return warehouseService.getMapPointHasProductByWarehouseId(warehouseId);
    }

    @GetMapping("/{warehouseId}/search/map-points/has-product")
    public List<String> getMapPointHasProductBy(@PathVariable Long warehouseId, StockSearchDto dto) {
        dto.setWarehouseId(warehouseId);
        return warehouseService.getMapPointHasProductBy(dto);
    }

    @GetMapping("/in-stock/product-types")
    public List<ProductTypeRespDto> getProductTypeInStock(StockSearchDto dto) {
        return warehouseService.getProductTypeInStock(dto);
    }

    @GetMapping("/in-stock/products")
    public List<ProductRespDto> getProductInStock(StockSearchDto dto) {
        return warehouseService.getProductInStock(dto);
    }

    @GetMapping("/in-stock/units")
    public List<ProductUnitProjectionDto> getUnitInStockBy(StockSearchDto dto) {
        return warehouseService.getUnitInStockBy(dto);
    }

    @GetMapping("/in-stock/product-details")
    public List<ProductDetailRespDto> getProductDetailInStockBy(StockSearchDto dto) {
        return warehouseService.getProductDetailInStockBy(dto);
    }

    @GetMapping("/in-stock/consignments")
    public List<String> getConsignmentInStockBy(StockSearchDto dto) {
        return warehouseService.getConsignmentInStockBy(dto);
    }

    @GetMapping("/in-stock/floor-details")
    public List<ProductFloorProjectionDto> getFloorDetailBy(StockSearchDto dto) {
        return warehouseService.getFloorDetailBy(dto);
    }

    @PostMapping("/search/quantity-in-stock")
    public List<ItemDto> getQuantityInFloor(@RequestBody List<ItemDto> dto) {
        return warehouseService.getQuantityInFloor(dto);
    }

    @GetMapping("/{id}")
    public WarehouseRespDto getById(@PathVariable Long id,
                                    SearchDto dto) {
        return warehouseService.getById(id, dto.getModifyDate());
    }

    @GetMapping("/{id}/locations")
    public Map<String, WarehouseLocationRespDto> getLocationMapsByWarehouseId(@PathVariable Long id, boolean hasFloorDetails) {
        return warehouseService.getLocationMapsByWarehouseId(id, hasFloorDetails);
    }

    @GetMapping("/report")
    public List<ReportProjectionDto> getReport(ReportSearchDto dto) {
        return warehouseService.getReport(dto);
    }

    @GetMapping("/card")
    public Page<CardRespDto> getCard(CardSearchDto dto) {
        return warehouseService.getCard(dto);
    }

    @GetMapping("/location/search/product")
    public Page<ProductFloorDetailProjectionDto> searchProductFloorDetail(ProductFloorSearchDto dto) {
        return warehouseService.searchProductFloorDetail(dto);
    }

    @GetMapping("/search")
    public Page<WarehouseRespDto> search(WarehouseSearchDto dto) {
        return warehouseService.search(dto);
    }

    @PostMapping("")
    public WarehouseRespDto add(@RequestBody WarehouseReqDto dto) {
        return warehouseService.add(dto);
    }

    @PutMapping("/{id}")
    public WarehouseRespDto update(@PathVariable Long id, @RequestBody WarehouseReqDto dto) {
        return warehouseService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return warehouseService.deleteById(id);
    }
}
