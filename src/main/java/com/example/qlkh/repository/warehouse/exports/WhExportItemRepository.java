package com.example.qlkh.repository.warehouse.exports;

import com.example.qlkh.entity.warehouse.exports.WhExportItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WhExportItemRepository extends JpaRepository<WhExportItem, Long> {
    @Modifying
    void deleteByParentId(Long parentId);
}
