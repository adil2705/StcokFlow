package com.inventory.InventoryManagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.InventoryManagement.DTO.OrderItemRequest;
import com.inventory.InventoryManagement.entity.AdminRegistration;
import com.inventory.InventoryManagement.entity.StockItem;
import com.inventory.InventoryManagement.entity.Wholesaler;
import com.inventory.InventoryManagement.entity.order.Order;
import com.inventory.InventoryManagement.entity.order.OrderItem;
import com.inventory.InventoryManagement.entity.order.OrderStatus;
import com.inventory.InventoryManagement.repository.OrderRepository;
import com.inventory.InventoryManagement.repository.StockItemRepo;
import com.inventory.InventoryManagement.repository.WholesalerRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockItemRepo stockItemRepository;

    @Autowired
    private WholesalerRepo wholesalerRepository;

    @Transactional
    public Order placeOrder(Long wholesalerId, OrderItemRequest singleItem, List<OrderItemRequest> multipleItems) {
    Wholesaler wholesaler = wholesalerRepository.findById(wholesalerId)
            .orElseThrow(() -> new RuntimeException("Wholesaler not found"));

    List<OrderItemRequest> orderItemRequests = new ArrayList<>();
    
    if (singleItem != null) {
        orderItemRequests.add(singleItem);
    }

    if (multipleItems != null) {
        orderItemRequests.addAll(multipleItems);
    }

    if (orderItemRequests.isEmpty()) {
        throw new RuntimeException("Order cannot be empty");
    }

    StockItem firstStockItem = stockItemRepository.findById(orderItemRequests.get(0).getStockItemId())
            .orElseThrow(() -> new RuntimeException("Stock item not found"));
    AdminRegistration admin = firstStockItem.getAdmin();

    Order order = new Order();
    order.setWholesaler(wholesaler);
    order.setAdmin(admin);
    order.setStatus(OrderStatus.PENDING);
    order.setOrderTimeDate(LocalDateTime.now());

    List<OrderItem> orderItems = new ArrayList<>();

    for (OrderItemRequest itemRequest : orderItemRequests) {
        StockItem stockItem = stockItemRepository.findById(itemRequest.getStockItemId())
                .orElseThrow(() -> new RuntimeException("Stock item not found"));

        if (stockItem.getQuantity() < itemRequest.getQuantity()) {
            throw new RuntimeException("Not enough stock available for item: " + stockItem.getName());
        }

        stockItem.setQuantity(stockItem.getQuantity() - itemRequest.getQuantity());
        stockItemRepository.save(stockItem);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setStockItem(stockItem);
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setTotalPrice(stockItem.getPrice() * itemRequest.getQuantity());

        orderItems.add(orderItem);
    }

    order.setOrderItems(orderItems);
    return orderRepository.save(order);
}


    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
