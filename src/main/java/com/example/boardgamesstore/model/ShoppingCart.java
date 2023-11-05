package com.example.boardgamesstore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<BoardGame, Integer> cartItems = new HashMap<>();

    public void addBoardGame(BoardGame boardGame, int quantity) {
        cartItems.put(boardGame, cartItems.getOrDefault(boardGame, 0) + quantity);
    }

    public BigDecimal calculateTotalCost() {
        return cartItems.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
