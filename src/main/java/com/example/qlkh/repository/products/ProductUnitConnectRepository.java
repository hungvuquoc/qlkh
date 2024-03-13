package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.dto.response.projection.ProductUnitProjectionDto;
import com.example.qlkh.entity.product.ProductUnitConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUnitConnectRepository extends JpaRepository<ProductUnitConnect, Long> {
    ProductUnitConnect getByIdAndProductId(Long id, Long productId);

    @Modifying
    @Query(
            value = " delete from tbl_product_unit_connect as pu " +
                    " where pu.product_id = :productId " +
                    " and pu.id in :ids ",
            nativeQuery = true
    )
    void deleteByIdAndProductIds(Long productId, List<Long> ids);

    @Query(
            value = " select * from tbl_product_unit_connect as puc " +
                    " where puc.product_id = :productId and puc.is_default = true ",
            nativeQuery = true
    )
    ProductUnitConnect getDefaultByProductId(Long productId);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId} " +
                    " ) " +
                    " select distinct " +
                    " pro_uni.id as 'id', " +
                    " uni.id as 'unitId', " +
                    " uni.code as 'unitCode', " +
                    " uni.name as 'unitName', " +
                    " pro_uni.ratio as 'ratio', " +
                    " pro_uni.is_default as 'default', " +
                    " pro_uni.is_use_report as 'useReport', " +
                    " pro_uni.deleted as 'deleted' " +
                    " from tbl_product_unit_connect as pro_uni " +
                    " join tbl_product_unit uni on pro_uni.unit_id = uni.id " +
                    " join tbl_product_floor pro_flo on pro_uni.id = pro_flo.unit_target_id " +
                    " join tbl_warehouse_floor as flo on pro_flo.floor_id = flo.id " +
                    " where pro_flo.quantity_target > 0 " +
                    " and pro_flo.product_id = :#{#dto.productId} " +
                    " and pro_flo.product_detail_id = :#{#dto.productDetailId} " +
                    " and flo.deleted = false " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<ProductUnitProjectionDto> getInStock(StockSearchDto dto);
}
