package com.inventory.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.InventoryManagement.entity.AdminRegistration;

@Repository
public interface AdminRegistartionRepo extends JpaRepository<AdminRegistration, Long> {
    AdminRegistration findByEmail(String email); 
}
