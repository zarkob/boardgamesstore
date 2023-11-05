package com.example.boardgamesstore.service

import com.example.boardgamesstore.model.BoardGame
import com.example.boardgamesstore.repository.BoardGameRepository
import spock.lang.Specification
import spock.lang.Shared

class BoardGameServiceSpec extends Specification {

    @Shared
    BoardGameRepository boardGameRepository = Mock(BoardGameRepository)

    @Shared
    BoardGameService boardGameService = new BoardGameService(boardGameRepository)

    def "findBoardGameByName should return game"() {
        given: "A game name and a mock repository"
        String gameName = "Catan"
        BoardGame catan = new BoardGame()
        catan.setName(gameName)
        boardGameRepository.findByName(gameName) >> { c -> Optional.of(catan) }

        when: "Searching for a game by name"
        Optional<BoardGame> foundGame = boardGameService.findBoardGameByName(gameName)

        then: "The game should be found and the name should be as expected"
        foundGame.isPresent()
        foundGame.get().getName() == gameName
    }
}

