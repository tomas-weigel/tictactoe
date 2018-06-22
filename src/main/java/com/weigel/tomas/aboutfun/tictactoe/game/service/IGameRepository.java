package com.weigel.tomas.aboutfun.tictactoe.game.service;

import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameStatus;
import com.weigel.tomas.aboutfun.tictactoe.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findGameByStatus(GameStatus status);

}
