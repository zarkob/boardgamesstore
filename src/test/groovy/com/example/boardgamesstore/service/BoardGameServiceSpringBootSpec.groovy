package com.example.boardgamesstore.service

import com.example.boardgamesstore.model.BoardGame
import com.example.boardgamesstore.repository.BoardGameRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class BoardGameServiceSpringBootSpec extends Specification {

    @Autowired
    BoardGameService boardGameService

    @SpringBean
    BoardGameRepository boardGameRepository = Mock()

    def 'test getAllBoardGames'() {
        given: 'Mock data for getAllBoardGames'
        boardGameRepository.findAll() >> List.of(
                new BoardGame(id: 1L, name: "Catan", price: 39.99, stock: 10),
                new BoardGame(id: 2L, name: "Ticket to Ride", price: 44.99, stock: 5)
        )

        when: 'Calling getAllBoardGames'
        def games = boardGameService.getAllBoardGames()

        then: 'Two board games should be returned'
        games.size() == 2
    }


    def 'test findBoardGameById'() {
        given: 'Mock data for findBoardGameById'
        Long gameId = 1L
        boardGameRepository.findById(gameId) >> Optional.of(new BoardGame(id: gameId, name: "Catan", price: 39.99, stock: 10))

        when: 'Calling findBoardGameById'
        def game = boardGameService.findBoardGameById(gameId)

        then: 'A board game should be found'
        game != null
        game.name == "Catan"
    }
}
