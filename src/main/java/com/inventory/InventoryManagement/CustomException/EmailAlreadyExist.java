package com.inventory.InventoryManagement.CustomException;

public class EmailAlreadyExist extends RuntimeException {
    public  EmailAlreadyExist(String message){
        super(message);
    }
}
