package com.inventory.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.InventoryManagement.entity.StockItem;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem, Long> {
    
}
