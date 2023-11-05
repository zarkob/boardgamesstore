package com.example.boardgamesstore.service;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardGameServiceTest {

    @Mock
    private BoardGameRepository boardGameRepository;

    @InjectMocks
    private BoardGameService boardGameService;

    @Test
    public void findBoardGameByNameShouldReturnGame() {
        // Given
        String gameName = "Catan";
        BoardGame catan = new BoardGame();
        catan.setName(gameName);
        when(boardGameRepository.findByName(gameName)).thenReturn(Optional.of(catan));

        // When
        Optional<BoardGame> foundGame = boardGameService.findBoardGameByName(gameName);

        // Then
        assertTrue(foundGame.isPresent(), "Game should be found");
        assertEquals(gameName, foundGame.get().getName(), "Game name should be Catan");
    }
}


