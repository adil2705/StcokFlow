package com.inventory.InventoryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.InventoryManagement.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByWholesalerId(Long wholesalerId);
    List<Order> findByAdminId(Long adminId);
}
