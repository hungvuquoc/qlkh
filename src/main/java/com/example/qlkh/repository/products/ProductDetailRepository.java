package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.entity.product.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Modifying
    @Query(
            value = " delete from tbl_product_detail as pd " +
                    " where pd.product_id = :productId " +
                    " and pd.id in :ids ",
            nativeQuery = true
    )
    void deleteByProductIdAndDetailIds(Long productId, List<Long> ids);

    List<ProductDetail> getByProductId(Long id);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId} " +
                    " ) " +
                    " select distinct pro_det.* from tbl_product_detail as pro_det " +
                    " join tbl_product_floor as pro_flo on pro_flo.product_detail_id = pro_det.id " +
                    " join tbl_warehouse_floor as flo on flo.id = pro_flo.floor_id " +
                    " where pro_flo.product_id = :#{#dto.productId} " +
                    " and pro_flo.quantity_target > 0 " +
                    " and flo.deleted = false " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<ProductDetail> getInStock(StockSearchDto dto);
}
