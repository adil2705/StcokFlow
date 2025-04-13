package com.inventory.InventoryManagement.service;

import java.util.List;
import java.util.Optional;

import com.inventory.InventoryManagement.entity.AdminRegistration;

public interface AdminRegistrationService {
    boolean checkEmailExist(String email);
    AdminRegistration registerAdmin(AdminRegistration admin);
    List<AdminRegistration> getallAdmins();
    Optional<AdminRegistration> getAdminById(Long id);
    AdminRegistration updateAdmin(long id , AdminRegistration updatedAdmin);
    void deleteAdmin(Long id);
    
}
