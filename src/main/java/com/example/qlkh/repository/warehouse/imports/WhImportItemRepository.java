package com.example.qlkh.repository.warehouse.imports;

import com.example.qlkh.entity.warehouse.imports.WhImportItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WhImportItemRepository extends JpaRepository<WhImportItem, Long> {
    @Modifying
    void deleteByParentId(Long parentId);
}
