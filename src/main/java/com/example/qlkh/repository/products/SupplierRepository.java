package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query(
            value = " select * from tbl_supplier as sp " +
                    " where ( " +
                    "     :#{#dto.keyword} is null " +
                    "     or sp.name like %:#{#dto.keyword}% " +
                    "     or sp.code like %:#{#dto.keyword}% " +
                    "   ) " +
                    " and (:#{#dto.deleted} is null or sp.deleted = :#{#dto.deleted}) " +
                    " order by sp.create_date desc " +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<Supplier> search(SearchDto dto);

    @Query(
            value = " select count(*) from tbl_supplier as sp " +
                    " where ( " +
                    "     :#{#dto.keyword} is null " +
                    "     or sp.name like %:#{#dto.keyword}% " +
                    "     or sp.code like %:#{#dto.keyword}% " +
                    "   ) " +
                    " and (:#{#dto.deleted} is null or sp.deleted = :#{#dto.deleted}) ",
            nativeQuery = true
    )
    long count(SearchDto dto);

    @Query(
            value = " select right(sp.code, 5) from tbl_supplier as sp " +
                    " order by right(sp.code, 5) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " select * from tbl_supplier as sp" +
                    " where sp.id in :ids ",
            nativeQuery = true
    )
    List<Supplier> getByIds(List<Long> ids);
}
