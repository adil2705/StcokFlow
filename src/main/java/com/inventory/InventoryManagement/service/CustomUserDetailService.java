package com.inventory.InventoryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagement.config.AdminConfig;
import com.inventory.InventoryManagement.config.WholesalerConfig;
import com.inventory.InventoryManagement.entity.AdminRegistration;
import com.inventory.InventoryManagement.entity.Wholesaler;
import com.inventory.InventoryManagement.repository.AdminRegistartionRepo;
import com.inventory.InventoryManagement.repository.WholesalerRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AdminRegistartionRepo adminrepo;

    @Autowired
    private WholesalerRepo wholesalerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminRegistration admin = adminrepo.findByEmail(email);  
        if (admin != null) {
            return new AdminConfig(admin);
        }

        Wholesaler wholesaler = wholesalerRepo.findByEmail(email);
        if (wholesaler != null) {
            return new WholesalerConfig(wholesaler);
        }

        throw new UsernameNotFoundException("User not found");
    }
}
