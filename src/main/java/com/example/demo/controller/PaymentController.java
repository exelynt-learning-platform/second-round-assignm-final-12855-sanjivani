package com.example.demo.controller;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public Payment pay(@RequestBody Payment payment) {

        payment.setStatus("SUCCESS"); // dummy

        return service.save(payment);
    }
}