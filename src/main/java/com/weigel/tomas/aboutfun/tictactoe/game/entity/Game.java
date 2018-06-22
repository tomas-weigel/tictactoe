package com.weigel.tomas.aboutfun.tictactoe.game.entity;

import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameStatus;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Game implements Serializable {

    public static final char[][] EMPTY_MATRIX = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}
    };

    public Game() {
        super();
        this.status = GameStatus.WAITING;
        this.matrix = EMPTY_MATRIX;
        this.currentPlayer = Player.X;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private Player currentPlayer;

    private int movesCount;

    private Player winner;

    private char[][] matrix;

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Game setStatus(GameStatus status) {
        this.status = status;
        return this;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public char[][] getMatrix() {
        return this.matrix;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isActive() {
        return this.status == GameStatus.ACTIVE;
    }

    public void victory() {
        this.setStatus(GameStatus.FINISHED);
        this.setWinner(currentPlayer);
    }

    public void tie() {
        this.setStatus(GameStatus.FINISHED);
    }

    public void rotatePlayer() {
        this.setCurrentPlayer(currentPlayer.rotate());
    }

    public void updateMatrix(char[][] newMatrix) {
        this.movesCount++;
        this.matrix = newMatrix;
    }
}
