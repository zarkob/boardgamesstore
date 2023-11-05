package com.example.boardgamesstore.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    @ParameterizedTest
    @CsvSource({
            "50.0, 2, 100.0",
            "30.0, 3, 90.0",
            "20.0, 5, 100.0"
    })
    public void testCalculateTotalCost(BigDecimal price, int quantity, BigDecimal expectedTotal) {
        // Arrange
        ShoppingCart shoppingCart = new ShoppingCart();
        BoardGame game = new BoardGame();
        game.setPrice(price);

        // Act
        shoppingCart.addBoardGame(game, quantity);

        // Assert
        assertEquals(expectedTotal, shoppingCart.calculateTotalCost());
    }
}
