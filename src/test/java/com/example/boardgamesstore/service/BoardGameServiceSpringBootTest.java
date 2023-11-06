package com.example.boardgamesstore.service;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
public class BoardGameServiceSpringBootTest {

    @Autowired
    private BoardGameService boardGameService;

    @MockBean
    private BoardGameRepository boardGameRepository;

    @Test
    public void testGetAllBoardGames() {
        // Arrange
        when(boardGameRepository.findAll()).thenReturn(List.of(
                new BoardGame(1L, "Catan", "Kosmos", new BigDecimal(39.99), 10),
                new BoardGame(2L, "Ticket to Ride", "Days of Wonder", new BigDecimal(44.99), 5)
        ));

        // Act
        List<BoardGame> games = boardGameService.getAllBoardGames();

        // Assert
        assertEquals(2, games.size());
    }

    @Test
    public void testFindBoardGameById() {
        // Arrange
        Long gameId = 1L;
        when(boardGameRepository.findById(gameId)).thenReturn(Optional.of(new BoardGame(gameId, "Catan", "Kosmos", new BigDecimal(39.99), 10)));

        // Act
        BoardGame game = boardGameService.findBoardGameById(gameId);

        // Assert
        assertNotNull(game);
        assertEquals("Catan", game.getName());
    }
}
