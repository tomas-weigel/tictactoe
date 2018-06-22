package com.weigel.tomas.aboutfun.tictactoe.game.service;

public interface IGameVictoryValidator {

    boolean isVictorious(char[][] matrix, char player, int row, int col);

}
