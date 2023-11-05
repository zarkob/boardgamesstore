package com.example.boardgamesstore.repository;

import com.example.boardgamesstore.model.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    Optional<BoardGame> findByName(String name);

    List<BoardGame> findByPublisher(String publisher);
}
