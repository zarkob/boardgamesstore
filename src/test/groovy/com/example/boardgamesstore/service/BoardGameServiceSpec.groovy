package com.example.boardgamesstore.service

import com.example.boardgamesstore.exceptions.OutOfStockException
import com.example.boardgamesstore.model.BoardGame
import com.example.boardgamesstore.repository.BoardGameRepository
import spock.lang.Specification
import spock.lang.Unroll


class BoardGameServiceSpec extends Specification {

    BoardGameRepository boardGameRepository = Mock()
    DiscountService discountService = Mock()
    BoardGameService boardGameService = new BoardGameService(boardGameRepository, discountService)



    /**
     * Exercise 4: Testing Exceptions
     */
    def "purchaseGame should decrement stock when game is available"() {
        given: "A game with available stock"
        Long gameId = 1L
        BoardGame game = new BoardGame(id: gameId, stock: 2)
        boardGameRepository.findById(gameId) >> Optional.of(game)
        boardGameRepository.save(game) >> { BoardGame updatedGame -> updatedGame }

        when: "Purchasing the game"
        BoardGame purchasedGame = boardGameService.purchaseGame(gameId)

        then: "The game's stock should be decremented"
        purchasedGame.stock == 1
    }

    def "purchaseGame should throw OutOfStockException when game is out of stock"() {
        given: "A game that is out of stock"
        Long gameId = 2L
        BoardGame game = new BoardGame(id: gameId, stock: 0)
        boardGameRepository.findById(gameId) >> Optional.of(game)

        when: "Purchasing the game"
        boardGameService.purchaseGame(gameId)

        then: "An OutOfStockException should be thrown"
        thrown(OutOfStockException)
    }

    def "purchaseGame should throw OutOfStockException when game does not exist"() {
        given: "A game that does not exist"
        Long gameId = 3L
        boardGameRepository.findById(gameId) >> Optional.empty()

        when: "Purchasing the game"
        boardGameService.purchaseGame(gameId)

        then: "An OutOfStockException should be thrown"
        thrown(OutOfStockException)
    }



    /**
     * Exercise 3: Mocks and stubbing
     */
    def "findBoardGameByName should return a board game with applied discount"() {
        given: "A game name and a mock repository"
        BoardGame chess = new BoardGame(name: "Chess", price: 50.0)
        boardGameRepository.findByName("Chess") >> Optional.of(chess)
        discountService.applyDiscount("Chess", 50.0) >> 40.0

        when: "Searching for a game by name"
        BoardGame foundGame = boardGameService.findBoardGameByName("Chess")

        then: "The game should be found and the discounted price should be applied"
        foundGame != null
        foundGame.name == "Chess"
        foundGame.price == 40.0
    }

    def "findBoardGameByName should throw an exception if the game is not found"() {
        given: "A mock repository and the game that doesn't exists"
        def mockRepository = Mock(BoardGameRepository)
        def mockDiscountService = Mock(DiscountService)
        def boardGameService = new BoardGameService(mockRepository, mockDiscountService)
        mockRepository.findByName("Chess") >> null

        when: "Searching for a game by name"
        boardGameService.findBoardGameByName("Chess")

        then: "The the exception should be thrown, and the method applyDiscount should never be called"
        thrown(RuntimeException)
        1 * mockRepository.findByName("Chess")
        0 * mockDiscountService.applyDiscount(_, _)
    }


    def "findBoardGameByName should return game"() {
        given: "A game name and a mock repository"
        String gameName = "Catan"
        BoardGame catan = new BoardGame()
        catan.setName(gameName)
        boardGameRepository.findByName(gameName) >> Optional.of(catan)

        when: "Searching for a game by name"
        BoardGame foundGame = boardGameService.findBoardGameByName(gameName)

        then: "The game should be found and the name should be as expected"
//        foundGame.
        foundGame.getName() == gameName
    }


    @Unroll
    def "findBoardGamesByPublisher should return correct count for publisher #publisher"() {
        given: "A publisher and a mock repository"
        BoardGame game = new BoardGame()
        game.setPublisher(publisher)
        boardGameRepository.findByPublisher(publisher) >> [game]

        when: "Searching for games by publisher"
        List<BoardGame> foundGames = boardGameService.findBoardGamesByPublisher(publisher)

        then: "The game count should match the expected count"
        foundGames.size() == expectedCount

        where:
        publisher        | expectedCount
        "Kosmos"         | 1
        "Days of Wonder" | 1
        "Unknown"        | 1
    }


}

