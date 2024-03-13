package com.example.qlkh.repository.warehouse.inventories;

import com.example.qlkh.entity.warehouse.inventories.WhInventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WhInventoryItemRepository extends JpaRepository<WhInventoryItem, Long> {
    @Modifying
    void deleteByParentId(Long parentId);
}
