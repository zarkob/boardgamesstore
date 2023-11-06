package com.example.boardgamesstore.service;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal applyDiscount(String gameName, BigDecimal price);
}
