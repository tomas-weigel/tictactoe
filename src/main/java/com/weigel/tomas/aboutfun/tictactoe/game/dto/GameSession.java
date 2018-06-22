package com.weigel.tomas.aboutfun.tictactoe.game.dto;

import com.weigel.tomas.aboutfun.tictactoe.game.entity.Game;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameErrors;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;

import java.util.Optional;

public class GameSession {

    public final Player player;

    public final Game game;

    public final Optional<GameErrors> gameErrors;

    public GameSession(final Player player, final Game game, final GameErrors gameErrors) {

        this.player = player;
        this.game = game;
        this.gameErrors = Optional.ofNullable(gameErrors);
    }

}