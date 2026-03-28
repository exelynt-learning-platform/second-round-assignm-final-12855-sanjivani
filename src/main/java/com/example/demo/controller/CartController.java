package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping
    public CartItem add(@RequestBody CartItem item) {
        return service.addToCart(item);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        service.removeItem(id);
        return "Item Removed";
    }
    
    @GetMapping("/{cartId}")
    public List<String> view(@PathVariable Long cartId) {
        return service.getCartDetails(cartId);
    }
}