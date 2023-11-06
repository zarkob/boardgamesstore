package com.example.boardgamesstore.controllers;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public List<BoardGame> getAllBoardGames() {
        return boardGameService.getAllBoardGames();
    }

    @GetMapping("/{id}")
    public BoardGame getBoardGameById(@PathVariable Long id) {
        return boardGameService.findBoardGameById(id);
    }

}
