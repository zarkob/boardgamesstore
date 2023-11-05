package com.example.boardgamesstore.repository;

import com.example.boardgamesstore.model.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    Optional<BoardGame> findByName(String name);
}
