package com.inventory.InventoryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.InventoryManagement.DTO.StockItemAdmin;
import com.inventory.InventoryManagement.service.StockItemService;


@RestController
@RequestMapping("/stockitem")
public class StockItemController {

   @Autowired
   StockItemService stockItemService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStock(@RequestBody StockItemAdmin Stock) {
        try {
            stockItemService.addStockItem(Stock);
            return new ResponseEntity<>("Stock item added successfully",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Stock item not added",HttpStatus.BAD_REQUEST);
        }    
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSTocks() {
        try {
            List<StockItemAdmin> getall=stockItemService.getAllStockItems();
            return new ResponseEntity<>(getall,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("cannot find",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
       try {
           StockItemAdmin stockItemAdmin=stockItemService.getStockItemById(id);
           return new ResponseEntity<>(stockItemAdmin,HttpStatus.OK);
       } catch (Exception e) {
              return new ResponseEntity<>("cannot find",HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody StockItemAdmin stockItemAdmin) {
        try {
            StockItemAdmin updatedStockItemAdmin=stockItemService.updateStockItem(id, stockItemAdmin);
            return new ResponseEntity<>(updatedStockItemAdmin,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Stock item not updated",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id){
        try {
            stockItemService.deleteStockItem(id);
            return new ResponseEntity<>("Stock item deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Stock item not deleted",HttpStatus.BAD_REQUEST);
        }
    }
    
    
}
