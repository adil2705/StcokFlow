package com.inventory.InventoryManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagement.DTO.StockItemAdmin;
import com.inventory.InventoryManagement.entity.AdminRegistration;
import com.inventory.InventoryManagement.entity.StockItem;
import com.inventory.InventoryManagement.repository.AdminRegistartionRepo;
import com.inventory.InventoryManagement.repository.StockItemRepo;

@Service
public class StockItemServiceImpl implements StockItemService {
    
    @Autowired
    StockItemRepo stockItemRepo;
    @Autowired
    AdminRegistartionRepo adminRepo;
    // @Autowired
    // StockItem StockItem;

    @Override
    public StockItemAdmin addStockItem(StockItemAdmin stockItemAdmin) {
       Optional<AdminRegistration> admin=adminRepo.findById(stockItemAdmin.getAdminId());
       
       if(admin.isPresent()){
       StockItem stockItem=new StockItem();
       stockItem.setName(stockItemAdmin.getName());
       stockItem.setPrice(stockItemAdmin.getPrice());
       stockItem.setQuantity(stockItemAdmin.getQuantity());
       stockItem.setDescription(stockItemAdmin.getDescription());
       stockItem.setAdmin(admin.get());
       StockItem savedStockItem=stockItemRepo.save(stockItem);
       return new StockItemAdmin(savedStockItem.getName(),savedStockItem.getDescription(),savedStockItem.getQuantity(),savedStockItem.getPrice(),savedStockItem.getAdmin().getId());
       }else{
           throw new RuntimeException("Admin not found");
        }
    }

    @Override
    public StockItemAdmin updateStockItem(long id, StockItemAdmin updatedStockItemAdmin) {
        Optional<StockItem> existingStockItem = stockItemRepo.findById(id);
        
        if (existingStockItem.isPresent()) {
            StockItem stockItem = existingStockItem.get();
            stockItem.setName(updatedStockItemAdmin.getName());
            stockItem.setDescription(updatedStockItemAdmin.getDescription());
            stockItem.setQuantity(updatedStockItemAdmin.getQuantity());
            stockItem.setPrice(updatedStockItemAdmin.getPrice());
            stockItem.setAdmin(adminRepo.findById(updatedStockItemAdmin.getAdminId()).get());
            StockItem savedStockItem=stockItemRepo.save(stockItem);
            return new StockItemAdmin(savedStockItem.getName(),savedStockItem.getDescription(),savedStockItem.getQuantity(),savedStockItem.getPrice(),savedStockItem.getAdmin().getId());
        } else {
            throw new RuntimeException("StockItem not found with id: " + id);
        }
    }

    @Override
    public void deleteStockItem(long id) {
        stockItemRepo.deleteById(id);
    }

    @Override
    public StockItemAdmin getStockItemById(long id) {
        Optional<StockItem> stockItem = stockItemRepo.findById(id);
        if (stockItem.isPresent()) {
            StockItem stockItem1 = stockItem.get();
            return new StockItemAdmin(stockItem1.getName(),stockItem1.getDescription(),stockItem1.getQuantity(),stockItem1.getPrice(),stockItem1.getAdmin().getId());
        } else {
            throw new RuntimeException("StockItem not found with id: " + id);
        }
    }

    @Override
    public List<StockItemAdmin> getAllStockItems() {
        List<StockItem> stockitems=stockItemRepo.findAll();
        List<StockItemAdmin> dto=new ArrayList<>(stockitems.size());
        for(int i=0; i<stockitems.size();i++){
            StockItem stockItem=stockitems.get(i);
            StockItemAdmin addToStockItem= new StockItemAdmin(
                stockItem.getName(),
                stockItem.getDescription(),
                stockItem.getQuantity(),
                stockItem.getPrice(),
                stockItem.getAdmin().getId()
            );
            dto.add(addToStockItem);
        }
        return dto;
    }
}
