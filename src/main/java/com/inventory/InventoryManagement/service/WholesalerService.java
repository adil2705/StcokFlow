package com.inventory.InventoryManagement.service;

import java.util.List;
import java.util.Optional;

import com.inventory.InventoryManagement.entity.Wholesaler;

public interface WholesalerService {

    boolean checkEmailExist(String email);
    Wholesaler registerWholesaler(Wholesaler wholesaler);
    List<Wholesaler> getAllWholesaler();
    Optional<Wholesaler> getWholesalerById(Long id);
    Wholesaler updateWholesaler(long id , Wholesaler updatedWholesaler);
    void deleteWholesaler(Long id);
    
}
