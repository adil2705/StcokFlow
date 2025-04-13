package com.inventory.InventoryManagement.controller;

import java.util.List;
import java.util.Map;

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

import com.inventory.InventoryManagement.CustomException.EmailAlreadyExist;
import com.inventory.InventoryManagement.entity.AdminRegistration;
import com.inventory.InventoryManagement.service.AdminRegistrationService;

@RestController
@RequestMapping("/admin")
public class AdminRegistrationController {

    @Autowired
    public AdminRegistrationService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> createAdmin(@RequestBody AdminRegistration admin) {
        try {
            adminService.registerAdmin(admin);
            return new ResponseEntity<>("Admin created successfully", HttpStatus.CREATED);
        
        }catch(EmailAlreadyExist ex){
            return new ResponseEntity<>("Email is Already Present",HttpStatus.BAD_REQUEST);
        } 
        catch (Exception e) {
            return new ResponseEntity<>("Admin not created", HttpStatus.BAD_REQUEST);

        }      
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAdmin() {
        try {
            List<AdminRegistration> getall=adminService.getallAdmins();
            return new ResponseEntity<>(getall,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            
            return new ResponseEntity<>(adminService.getAdminById(id),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("admin not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminRegistration admin) {
        try {
            AdminRegistration updatedAdmin=adminService.updateAdmin(id, admin);
            return new ResponseEntity<>(updatedAdmin,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("cant find admin", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable long id){
        try {
            adminService.deleteAdmin(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no admin found",HttpStatus.BAD_REQUEST);
        }
    }

}
