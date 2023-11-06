package com.example.boardgamesstore.controllers;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.service.BoardGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(BoardGameController.class)
class BoardGameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardGameService boardGameService;

    @Test
    public void testGetAllBoardGames() throws Exception {
        // Arrange
        when(boardGameService.getAllBoardGames()).thenReturn(List.of(
                new BoardGame(1L, "Catan", "Kosmos", new BigDecimal(39.99), 10),
                new BoardGame(2L, "Ticket to Ride", "Days of Wonder", new BigDecimal(44.99), 5)
        ));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boardgames"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetBoardGameById() throws Exception {
        // Arrange
        Long gameId = 1L;
        when(boardGameService.findBoardGameById(gameId))
                .thenReturn(new BoardGame(1L, "Catan", "Kosmos", new BigDecimal(39.99), 10));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boardgames/{id}", gameId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Catan"));
    }

}
