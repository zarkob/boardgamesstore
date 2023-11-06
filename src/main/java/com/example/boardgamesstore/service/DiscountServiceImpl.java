package com.example.boardgamesstore.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Override
    public BigDecimal applyDiscount(String gameName, BigDecimal price) {
        if ("Chess".equalsIgnoreCase(gameName)) {
            return price.multiply(new BigDecimal("0.8"));  // Apply a 20% discount
        }
        return price;  // No discount for other games
    }
}
