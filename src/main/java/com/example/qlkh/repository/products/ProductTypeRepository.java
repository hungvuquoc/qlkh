package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.entity.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    @Query(
            value = " select * from tbl_product_type as pt " +
                    " where ( " +
                    " :#{#dto.keyword} is null " +
                    " or pt.code like %:#{#dto?.keyword}% " +
                    " or pt.name like %:#{#dto?.keyword}% " +
                    " ) " +
                    " order by pt.create_date desc" +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<ProductType> search(SearchDto dto);

    @Query(
            value = " select count(*) from tbl_product_type as pt " +
                    " where ( " +
                    " :#{#dto.keyword} is null " +
                    " or pt.code like %:#{#dto?.keyword}% " +
                    " or pt.name like %:#{#dto?.keyword}% " +
                    " ) ",
            nativeQuery = true
    )
    long count(SearchDto dto);

    @Query(
            value = " select right(pt.code, 5) from tbl_product_type as pt " +
                    " order by right(pt.code, 5) desc " +
                    " limit 1",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "    select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "    where wh.id = :#{#dto.warehouseId} " +
                    " )," +
                    " types as " +
                    " ( " +
                    "    select wh_pro_typ.type_id from tbl_warehouse_product_type_connect as wh_pro_typ " +
                    "    where :#{#dto.andInWarehouseId} is not null " +
                    "    and  wh_pro_typ.warehouse_id = :#{#dto.andInWarehouseId} " +
                    " ) " +
                    " select distinct pro_typ.* from tbl_product_type as pro_typ " +
                    " join tbl_product as pro on pro_typ.id = pro.type_id " +
                    " join tbl_product_floor pro_flo on pro.id = pro_flo.product_id " +
                    " join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    " where flo.deleted = false " +
                    " and pro_flo.quantity_target > 0 " +
                    " and flo.code rlike (select * from warehouse) " +
                    " and if (:#{#dto.andInWarehouseId} is null, true , pro_typ.id in (select * from types)) ",
            nativeQuery = true
    )
    List<ProductType> getInStock(StockSearchDto dto);

    @Query(
            value = " select * from tbl_product_type as pro_typ " +
                    " where pro_typ.id in :ids " +
                    " and pro_typ.deleted = false ",
            nativeQuery = true
    )
    List<ProductType> getByIds(List<Long> ids);
}
