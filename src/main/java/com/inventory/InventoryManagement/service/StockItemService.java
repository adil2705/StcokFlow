package com.inventory.InventoryManagement.service;

import java.util.List;

import com.inventory.InventoryManagement.DTO.StockItemAdmin;

public interface  StockItemService {
    
    StockItemAdmin addStockItem(StockItemAdmin stockItemAdmin);
    StockItemAdmin updateStockItem(long id, StockItemAdmin updatedStockItemAdmin);
    void deleteStockItem(long id);
    StockItemAdmin getStockItemById(long id);
    List<StockItemAdmin> getAllStockItems();
}
