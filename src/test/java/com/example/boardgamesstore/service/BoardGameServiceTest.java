package com.example.boardgamesstore.service;

import com.example.boardgamesstore.exceptions.OutOfStockException;
import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardGameServiceTest {

    @Mock
    private BoardGameRepository mockBoardGameRepository;

    @Mock
    private DiscountService mockDiscountService;

    @InjectMocks
    private BoardGameService boardGameService;


    /**
     * Exercise 4: Testing Exceptions
     */
    @Test
    public void testPurchaseGameSuccessful() throws OutOfStockException {
        // Arrange
        Long gameId = 1L;
        BoardGame game = new BoardGame();
        game.setId(gameId);
        game.setStock(2);

        when(mockBoardGameRepository.findById(gameId)).thenReturn(Optional.of(game));
        when(mockBoardGameRepository.save(game)).thenReturn(game);

        // Act
        BoardGame purchasedGame = boardGameService.purchaseGame(gameId);

        // Assert
        assertEquals(1, purchasedGame.getStock());
    }

    @Test
    public void testPurchaseGameOutOfStock() {
        // Arrange
        Long gameId = 2L;
        BoardGame game = new BoardGame();
        game.setId(gameId);
        game.setStock(0);

        when(mockBoardGameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Act and Assert
        assertThrows(OutOfStockException.class, () -> boardGameService.purchaseGame(gameId));
    }

    @Test
    public void testPurchaseGameGameNotExists() {
        // Arrange
        Long gameId = 3L;

        when(mockBoardGameRepository.findById(gameId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(OutOfStockException.class, () -> boardGameService.purchaseGame(gameId));
    }




    /**
     * Exercise 3: Mocks and stubbing
     */
    @Test
    public void findBoardGameByNameShouldReturnGame() {
        // Given
        String gameName = "Catan";
        BoardGame catan = new BoardGame();
        catan.setName(gameName);
        when(mockBoardGameRepository.findByName(gameName)).thenReturn(Optional.of(catan));

        // When
        BoardGame foundGame = boardGameService.findBoardGameByName(gameName);

        // Then
        assertNotNull(foundGame, "Game should be found");
        assertEquals(gameName, foundGame.getName(), "Game name should be Catan");
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
        when(mockBoardGameRepository.findByPublisher(publisher)).thenReturn(List.of(game));

        // When
        List<BoardGame> foundGames = boardGameService.findBoardGamesByPublisher(publisher);

        // Then
        assertEquals(expectedCount, foundGames.size(), "Game count should match expected count");
    }

    /**
     * Exercise 3: Mocks and stubbing
     */
    @Test
    public void testFindBoardGameByName() {
        // Arrange
        BoardGameService boardGameService = new BoardGameService(mockBoardGameRepository, mockDiscountService);
        BoardGame mockGame = new BoardGame();
        mockGame.setName("Chess");
        when(mockBoardGameRepository.findByName("Chess")).thenReturn(Optional.of(mockGame));

        // Act
        BoardGame result = boardGameService.findBoardGameByName("Chess");

        // Assert
        assertNotNull(result, "Game should be found");
        assertEquals("Chess", result.getName());
        verify(mockBoardGameRepository, times(1)).findByName("Chess");
    }

    @Test
    public void testFindBoardGameByName_WithDiscount() {
        // Arrange
        BoardGameService boardGameService = new BoardGameService(mockBoardGameRepository, mockDiscountService);

        BoardGame mockGame = new BoardGame();
        mockGame.setName("Chess");
        mockGame.setPrice(BigDecimal.valueOf(50.0));

        when(mockBoardGameRepository.findByName("Chess")).thenReturn(Optional.of(mockGame));
        when(mockDiscountService.applyDiscount("Chess", BigDecimal.valueOf(50.0))).thenReturn(BigDecimal.valueOf(40.0));

        // Act
        BoardGame result = boardGameService.findBoardGameByName("Chess");

        // Assert
        assertNotNull(result, "Game should be found");
        assertEquals("Chess", result.getName());
        assertEquals(BigDecimal.valueOf(40.0), result.getPrice());
        verify(mockBoardGameRepository, times(1)).findByName("Chess");
        verify(mockDiscountService, times(1)).applyDiscount("Chess", BigDecimal.valueOf(50.0));
    }
}


