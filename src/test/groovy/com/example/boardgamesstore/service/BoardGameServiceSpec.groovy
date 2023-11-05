package com.example.boardgamesstore.service

import com.example.boardgamesstore.model.BoardGame
import com.example.boardgamesstore.repository.BoardGameRepository
import spock.lang.Specification
import spock.lang.Unroll


class BoardGameServiceSpec extends Specification {


    BoardGameRepository boardGameRepository = Mock()
    BoardGameService boardGameService = new BoardGameService(boardGameRepository)


    def "findBoardGameByName should return game"() {
        given: "A game name and a mock repository"
        String gameName = "Catan"
        BoardGame catan = new BoardGame()
        catan.setName(gameName)
        boardGameRepository.findByName(gameName) >> Optional.of(catan)

        when: "Searching for a game by name"
        Optional<BoardGame> foundGame = boardGameService.findBoardGameByName(gameName)

        then: "The game should be found and the name should be as expected"
        foundGame.isPresent()
        foundGame.get().getName() == gameName
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

