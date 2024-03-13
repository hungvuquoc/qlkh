package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.ProductSearchDto;
import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            value = " select  p.* from tbl_product as p " +
                    " left join tbl_product_group_connect pg on p.id = pg.product_id " +
                    " left join tbl_product_unit_connect pu on p.id = pu.product_id " +
                    " left join tbl_product_supplier_connect ps on p.id = ps.product_id " +
                    " where ( " +
                    "  :#{#dto.keyword} is null " +
                    "   or p.name like %:#{#dto?.keyword}% " +
                    "   or p.code like %:#{#dto?.keyword}% " +
                    " ) " +
                    " and ((:#{#dto.typeIds}) is null or p.type_id in (:#{#dto.typeIds})) " +
                    " and ((:#{#dto.groupIds}) is null or pg.group_id in (:#{#dto.groupIds})) " +
                    " and ((:#{#dto.unitIds}) is null or pu.unit_id in (:#{#dto.unitIds})) " +
                    " and ((:#{#dto.supplierIds}) is null or ps.supplier_id in (:#{#dto.supplierIds})) " +
                    " group by p.id, p.create_date " +
                    " order by p.create_date desc " +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<Product> search(ProductSearchDto dto);

    @Query(
            value = " select  count(distinct p.id) from tbl_product as p " +
                    " left join tbl_product_group_connect pg on p.id = pg.product_id " +
                    " left join tbl_product_unit_connect pu on p.id = pu.product_id " +
                    " left join tbl_product_supplier_connect ps on p.id = ps.product_id " +
                    " where ( " +
                    "  :#{#dto.keyword} is null " +
                    "   or p.name like %:#{#dto?.keyword}% " +
                    "   or p.code like %:#{#dto?.keyword}% " +
                    " ) " +
                    " and ((:#{#dto.typeIds}) is null or p.type_id in (:#{#dto.typeIds})) " +
                    " and ((:#{#dto.groupIds}) is null or pg.group_id in (:#{#dto.groupIds})) " +
                    " and ((:#{#dto.unitIds}) is null or pu.unit_id in (:#{#dto.unitIds})) " +
                    " and ((:#{#dto.supplierIds}) is null or ps.supplier_id in (:#{#dto.supplierIds})) ",
            nativeQuery = true
    )
    long count(ProductSearchDto dto);

    @Query(
            value = " select right(p.code, 5) from tbl_product as p " +
                    " order by right(p.code, 5) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " with warehouse as " +
                    " (" +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId} " +
                    " ) " +
                    " select distinct pro.* from tbl_product as pro " +
                    " join tbl_product_floor as pro_flo on pro_flo.product_id = pro.id " +
                    " join tbl_warehouse_floor as flo on flo.id = pro_flo.floor_id " +
                    " where pro_flo.quantity_target > 0 " +
                    " and (:#{#dto.typeId} is null or pro.type_id = :#{#dto.typeId}) " +
                    " and (:#{#dto.deleted} is null or (:#{#dto.deleted} = false and pro_flo.quantity_target > 0) or (:#{#dto.deleted} = true and pro_flo.quantity_target <= 0)) " +
                    " and (:#{#dto.deleted} is null or flo.deleted = :#{#dto.deleted}) " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<Product> getInStock(StockSearchDto dto);
}
