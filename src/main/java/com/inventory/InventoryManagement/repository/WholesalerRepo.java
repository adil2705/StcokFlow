package com.inventory.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.InventoryManagement.entity.Wholesaler;

@Repository
public interface WholesalerRepo  extends JpaRepository<Wholesaler, Long>{
    Wholesaler findByEmail(String email);
}
