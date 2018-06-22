package com.weigel.tomas.aboutfun.tictactoe.game.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum GameErrors {

    INCORRECT_GAME_STATE_NEED_TO_WAIT("Incorrect game state, need to wait for second player."),
    INCORRECT_GAME_STATE_GAME_FINISHED("Incorrect game state, game is already finished."),
    INCORRECT_ROW_OR_COLUMN("Incorrect row or column"),
    INCORRECT_TURN("Please wait for your turn"),
    POSITION_ALREADY_TAKEN("Position is already taken");

    private String errorMessage;

    GameErrors(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return this.ordinal();
    }
}
