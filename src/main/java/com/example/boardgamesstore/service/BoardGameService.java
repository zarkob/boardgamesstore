package com.example.boardgamesstore.service;

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
