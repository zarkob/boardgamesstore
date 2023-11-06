package com.example.boardgamesstore.service;

import com.example.boardgamesstore.exceptions.OutOfStockException;
import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BoardGameService {


    private BoardGameRepository boardGameRepository;
    private DiscountService discountService;
    @Autowired
    public BoardGameService(BoardGameRepository boardGameRepository, DiscountService discountService) {
        this.boardGameRepository = boardGameRepository;
        this.discountService = discountService;
    }

    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.findAll();
    }

    public BoardGame findBoardGameById(Long id) {
        BoardGame boardGame = null;

        Optional<BoardGame> optionalBoardGame = boardGameRepository.findById(id);
        if (!optionalBoardGame.isPresent()) {
            throw new RuntimeException("Game not found");
        }
        boardGame = optionalBoardGame.get();
        return boardGame;
    }

    public BoardGame purchaseGame(Long gameId) throws OutOfStockException {
        Optional<BoardGame> optionalGame = boardGameRepository.findById(gameId);

        if (optionalGame.isPresent()) {
            BoardGame game = optionalGame.get();
            if (game.getStock() > 0) {
                game.setStock(game.getStock() - 1);
                return boardGameRepository.save(game);
            } else {
                throw new OutOfStockException("The game is out of stock.");
            }
        } else {
            throw new OutOfStockException("The game does not exist.");
        }
    }

    public BoardGame findBoardGameByName(String name) {
        BoardGame boardGame = null;

        Optional<BoardGame> optionalBoardGame = boardGameRepository.findByName(name);
        if (!optionalBoardGame.isPresent()) {
            throw new RuntimeException("Game not found");
        }

        boardGame = optionalBoardGame.get();
        BigDecimal discountedPrice = discountService.applyDiscount(name, boardGame.getPrice());
        boardGame.setPrice(discountedPrice);

        return boardGame;
    }

    public List<BoardGame> findBoardGamesByPublisher(String publisher) {
        return boardGameRepository.findByPublisher(publisher);
    }
}
