package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    @Query(
            value = " select * from tbl_product_group as pg " +
                    " where (" +
                    "  :#{#dto.keyword} is null " +
                    "  or pg.name like %:#{#dto?.keyword}% " +
                    "  or pg.code like %:#{#dto?.keyword}% " +
                    " ) " +
                    " order by pg.create_date desc " +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<ProductGroup> search(SearchDto dto);

    @Query(
            value = " select count(*) from tbl_product_group as pg " +
                    " where (" +
                    "  :#{#dto.keyword} is null " +
                    "  or pg.name like %:#{#dto?.keyword}% " +
                    "  or pg.code like %:#{#dto?.keyword}% " +
                    " ) ",
            nativeQuery = true
    )
    long count(SearchDto dto);

    @Query(
            value = " select right(pg.code, 5) from tbl_product_group as pg " +
                    " order by right(pg.code, 5) desc " +
                    " limit 1",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " select * from tbl_product_group as pg " +
                    " where pg.id in :ids ",
            nativeQuery = true
    )
    List<ProductGroup> getByIds(List<Long> ids);
}
