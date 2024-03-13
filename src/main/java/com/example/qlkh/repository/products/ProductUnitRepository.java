package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long> {
    @Query(
            value = " select * from tbl_product_unit as pu " +
                    " where ( " +
                    "     :#{#dto.keyword} is null " +
                    "     or pu.name like %:#{#dto.keyword}% " +
                    "     or pu.code like %:#{#dto.keyword}% " +
                    "   ) " +
                    " order by pu.create_date desc " +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<ProductUnit> search(SearchDto dto);

    @Query(
            value = " select count(*) from  tbl_product_unit as pu " +
                    " where ( " +
                    "     :#{#dto.keyword} is null " +
                    "     or pu.name like %:#{#dto.keyword}% " +
                    "     or pu.code like %:#{#dto.keyword}% " +
                    "   ) ",
            nativeQuery = true
    )
    long count(SearchDto dto);

    @Query(
            value = " select right(pu.code, 5) from tbl_product_unit as pu " +
                    " order by right(pu.code, 5) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " select uni.* from tbl_product_unit_connect as pro_uni " +
                    " join tbl_product_unit as uni on pro_uni.unit_id = uni.id " +
                    " where pro_uni.product_id = :productId and pro_uni.is_use_report is true ",
            nativeQuery = true
    )
    ProductUnit getDefault(Long productId);
}
