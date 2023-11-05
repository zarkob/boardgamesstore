package com.example.boardgamesstore.service;

import com.example.boardgamesstore.model.BoardGame;
import com.example.boardgamesstore.repository.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardGameService {


    private BoardGameRepository boardGameRepository;
    @Autowired
    public BoardGameService(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    public Optional<BoardGame> findBoardGameByName(String name) {
        return boardGameRepository.findByName(name);
    }

    public List<BoardGame> findBoardGamesByPublisher(String publisher) {
        return boardGameRepository.findByPublisher(publisher);
    }
}
