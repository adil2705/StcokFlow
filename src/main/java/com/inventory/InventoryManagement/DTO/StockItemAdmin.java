package com.inventory.InventoryManagement.DTO;


public class StockItemAdmin {
    
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Long adminId;

    public StockItemAdmin(String name, String description, int quantity, double price, Long adminId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

}
