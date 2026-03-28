package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    public CartItem addToCart(CartItem item) {
        return cartRepo.save(item);
    }
    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    public List<String> getCartDetails(Long cartId) {

        List<CartItem> items = cartRepo.findByCartId(cartId);
        List<String> result = new ArrayList<>();

        for (CartItem item : items) {

            Product p = productRepo.findById(item.getProductId()).orElse(null);

            if (p != null) {
                double total = p.getPrice() * item.getQuantity();

                result.add("Product: " + p.getName() +
                        " | Price: " + p.getPrice() +
                        " | Qty: " + item.getQuantity() +
                        " | Total: " + total);
            }
        }
        return result;
    }
}