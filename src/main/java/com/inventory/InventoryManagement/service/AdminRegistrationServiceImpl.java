package com.inventory.InventoryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagement.CustomException.EmailAlreadyExist;
import com.inventory.InventoryManagement.entity.AdminRegistration;
import com.inventory.InventoryManagement.repository.AdminRegistartionRepo;

@Service
public class AdminRegistrationServiceImpl implements AdminRegistrationService {

    @Autowired
    AdminRegistartionRepo adminRepo;

    @Override
    public boolean checkEmailExist(String email) {
        AdminRegistration existingemail= adminRepo.findByEmail(email);
        return existingemail!=null;
    }


    @Override
    public AdminRegistration registerAdmin(AdminRegistration admin) {
        if(checkEmailExist(admin.getEmail())){
           throw new EmailAlreadyExist("email already exist"+admin.getEmail());
        }
        BCryptPasswordEncoder passwordencoder=new BCryptPasswordEncoder();
        admin.setPassword(passwordencoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
  
    }

    @Override
    public List<AdminRegistration> getallAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Optional<AdminRegistration> getAdminById(Long id) {
        return adminRepo.findById(id); 
    }

    @Override
    public AdminRegistration updateAdmin(long id, AdminRegistration updatedAdmin) {
        Optional<AdminRegistration> existingAdmin = getAdminById(id);
        
        if (existingAdmin.isPresent()) {
            AdminRegistration admin = existingAdmin.get();
            admin.setName(updatedAdmin.getName());
            admin.setEmail(updatedAdmin.getEmail());
    
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(updatedAdmin.getPassword());
            admin.setPassword(hashedPassword);
    
            admin.setAddress(updatedAdmin.getAddress());
            admin.setPhonenumber(updatedAdmin.getPhonenumber());
            admin.setRole(updatedAdmin.getRole());
    
            return adminRepo.save(admin);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }
    @Override
    public void deleteAdmin(Long id){
        adminRepo.deleteById(id);
    }
    
}
