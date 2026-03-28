package com.example.demo.dto;



public class PaymentDTO {

    private Long orderId;
    private double amount;

    // getters & setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
