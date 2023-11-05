package com.example.boardgamesstore.model

import spock.lang.Specification
import spock.lang.Unroll

class ShoppingCartSpec extends Specification {

    @Unroll
    def "should calculate correct total cost when price is #price, quantity is #quantity, expected total is #expectedTotal"() {
        given: "a shopping cart and a board game"
        ShoppingCart shoppingCart = new ShoppingCart()
        BoardGame game = new BoardGame()
        game.price = price

        when: "a board game is added with a certain quantity"
        shoppingCart.addBoardGame(game, quantity)

        then: "the total cost should be correct"
        shoppingCart.calculateTotalCost() == expectedTotal

        where:
        price       | quantity | expectedTotal
        50.0        | 2        | 100.0
        30.0        | 3        | 90.0
        20.0        | 5        | 100.0
    }

}
