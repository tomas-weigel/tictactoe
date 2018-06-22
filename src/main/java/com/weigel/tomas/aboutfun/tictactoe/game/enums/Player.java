package com.weigel.tomas.aboutfun.tictactoe.game.enums;

public enum Player {

    X, O;

    public char toChar() {
        return this.toString().charAt(0);
    }

    public Player rotate() {
        return this == Player.X ? Player.O : Player.X;
    }

}
