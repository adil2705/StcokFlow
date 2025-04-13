package com.inventory.InventoryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagement.CustomException.EmailAlreadyExist;
import com.inventory.InventoryManagement.entity.Wholesaler;
import com.inventory.InventoryManagement.repository.WholesalerRepo;

@Service
public class WholesalerServiceImpl implements WholesalerService {

    @Autowired
    WholesalerRepo wholesalerRepo;

    @Override
    public boolean checkEmailExist(String email) {
        Wholesaler existingemail= wholesalerRepo.findByEmail(email);
        return existingemail!=null;
    }
    @Override
    public Wholesaler registerWholesaler(Wholesaler wholesaler) {
        if(checkEmailExist(wholesaler.getEmail())){
           throw new EmailAlreadyExist("email already exist"+wholesaler.getEmail());
        }
        BCryptPasswordEncoder passwordencoder=new BCryptPasswordEncoder();
        wholesaler.setPassword(passwordencoder.encode(wholesaler.getPassword()));
        return wholesalerRepo.save(wholesaler);
    }

    @Override
    public List<Wholesaler> getAllWholesaler() {
        return wholesalerRepo.findAll();
    }

    @Override
    public Optional<Wholesaler> getWholesalerById(Long id) {

        return wholesalerRepo.findById(id);
    }

    @Override
    public Wholesaler updateWholesaler(long id, Wholesaler updatedWholesaler) {
        Optional<Wholesaler> existingWholesaler = getWholesalerById(id);
        
        if (existingWholesaler.isPresent()) {
            Wholesaler wholesaler = existingWholesaler.get();
            wholesaler.setName(updatedWholesaler.getName());
            wholesaler.setEmail(updatedWholesaler.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(updatedWholesaler.getPassword());
            wholesaler.setPassword(hashedPassword);
            wholesaler.setAddress(updatedWholesaler.getAddress());
            wholesaler.setPhonenumber(updatedWholesaler.getPhonenumber());
            wholesaler.setRole(updatedWholesaler.getRole());
            return wholesalerRepo.save(wholesaler);
        }
        return null;
    }

    @Override
    public void deleteWholesaler(Long id) {
        wholesalerRepo.deleteById(id);
    }
    
}
