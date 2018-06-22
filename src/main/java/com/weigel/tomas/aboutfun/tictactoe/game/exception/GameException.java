package com.weigel.tomas.aboutfun.tictactoe.game.exception;

import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameErrors;

public class GameException extends Exception {

    public final GameErrors gameErrors;

    public GameException(final GameErrors gameErrors) {
        super(gameErrors.getErrorMessage());
        this.gameErrors = gameErrors;
    }
}
