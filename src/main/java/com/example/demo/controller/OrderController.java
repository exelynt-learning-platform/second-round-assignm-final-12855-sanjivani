package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // 🛒 Checkout API (Order + Payment Flow)
    @PostMapping("/checkout")
    public String checkout(@RequestBody Order order) {

        // Step 1: Save Order
        order.setStatus("CREATED");
        Order savedOrder = orderRepository.save(order);

        // Step 2: Simulate Payment
        Payment payment = new Payment();
        payment.setOrderId(savedOrder.getId());
        payment.setAmount(savedOrder.getTotalAmount());
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        // Step 3: Update Order Status
        savedOrder.setStatus("PAID");
        orderRepository.save(savedOrder);

        return "Order placed successfully!";
    }

    // 📄 Get all orders
    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 📄 Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}