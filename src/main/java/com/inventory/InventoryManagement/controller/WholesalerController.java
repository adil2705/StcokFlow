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
import com.inventory.InventoryManagement.entity.Wholesaler;
import com.inventory.InventoryManagement.service.WholesalerService;


@RestController
@RequestMapping("/wholesaler")
public class WholesalerController {

    @Autowired
    WholesalerService wholesalerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Wholesaler wholesaler) {
            try {
                wholesalerService.registerWholesaler(wholesaler);
                return new ResponseEntity<>("Wholesaler created successfully", HttpStatus.CREATED);
                
            } catch (EmailAlreadyExist ex) {
                return new ResponseEntity<>("Email is Already Present", HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(Map.of("Wholesaler not created",e.getMessage()), HttpStatus.BAD_REQUEST);
            }  
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAdmin() {
        try {
            List<Wholesaler> getall= wholesalerService.getAllWholesaler();
            return new ResponseEntity<>(getall,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>("cannot find",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            
            return new ResponseEntity<>(wholesalerService.getWholesalerById(id),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("wholesaler not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWholesaler(@PathVariable Long id, @RequestBody Wholesaler wholesaler) {
        try {
            Wholesaler updatedWholesaler=wholesalerService.updateWholesaler(id, wholesaler);
            return new ResponseEntity<>(updatedWholesaler,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("cant find wholesaler", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWholesaler(@PathVariable long id){
        try {
            wholesalerService.deleteWholesaler(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no wholesaler found",HttpStatus.BAD_REQUEST);
        }
    }

}
