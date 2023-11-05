package com.example.boardgamesstore.service;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @ParameterizedTest
    @CsvSource({
            "Kosmos, 1",
            "Days of Wonder, 1",
            "Unknown, 1"
    })
    public void findBoardGamesByPublisherShouldReturnCorrectCount(String publisher, int expectedCount) {
        // Given
        BoardGame game = new BoardGame();
        game.setPublisher(publisher);
        when(boardGameRepository.findByPublisher(publisher)).thenReturn(List.of(game));

        // When
        List<BoardGame> foundGames = boardGameService.findBoardGamesByPublisher(publisher);

        // Then
        assertEquals(expectedCount, foundGames.size(), "Game count should match expected count");
    }
}


