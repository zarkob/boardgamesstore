package com.example.boardgamesstore.controllers

import com.example.boardgamesstore.model.BoardGame
import com.example.boardgamesstore.service.BoardGameService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BoardGameControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    BoardGameService boardGameService = Mock()


    def "test getAllBoardGames"() {
        given: 'Mock data for getAllBoardGames'
        boardGameService.getAllBoardGames() >> List.of(
                new BoardGame(id: 1L, name: "Catan", price: 39.99, stock: 10),
                new BoardGame(id: 2L, name: "Ticket to Ride", price: 44.99, stock: 5)
        )

        when: 'Calling /api/boardgames endpoint'
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/api/boardgames"))

        then: 'Status is OK and contains two board games'
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Catan")))
                .andExpect(content().string(containsString("Ticket to Ride")))

    }

    def "test getBoardGameById"() {
        given: 'Mock data for getBoardGameById'
        Long gameId = 1L
        boardGameService.findBoardGameById(gameId) >> new BoardGame(id: gameId, name: "Catan", price: 39.99, stock: 10)

        when: 'Calling /api/boardgames/{id} endpoint'
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/api/boardgames/{id}", gameId))

        then: 'Status is OK and the board game name is "Catan"'
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Catan")))
    }
}
