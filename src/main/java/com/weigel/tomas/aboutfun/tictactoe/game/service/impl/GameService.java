package com.weigel.tomas.aboutfun.tictactoe.game.service.impl;

import com.weigel.tomas.aboutfun.tictactoe.game.dto.GameSession;
import com.weigel.tomas.aboutfun.tictactoe.game.entity.Game;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameErrors;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameStatus;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;
import com.weigel.tomas.aboutfun.tictactoe.game.exception.GameException;
import com.weigel.tomas.aboutfun.tictactoe.game.service.IGameVictoryValidator;
import com.weigel.tomas.aboutfun.tictactoe.game.service.IGameRepository;
import com.weigel.tomas.aboutfun.tictactoe.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService implements IGameService {

    @Autowired
    IGameRepository gameRepository;

    @Autowired
    IGameVictoryValidator gameLogicService;

    public static final char EMPTY_MATRIX_POSITION = '0';

    /**
     * Players will join a match, if there is one created or will create new one,
     * if there isn’t anyone waiting for another player
     *
     * @return GameSession contains a response to players actions
     */
    @Override
    @Transactional
    public GameSession joinBattle() {
        Game game = gameRepository
                .findGameByStatus(GameStatus.WAITING)
                .map(this::startGame)
                .orElseGet(this::createNewGame);

        return new GameSession(game.isActive() ? Player.O : Player.X, game, null);
    }

    /**
     * Places marker on game matrix at specified position.
     *
     * @param gameId - game identifier
     * @param player - player identifier
     * @param row - row the player want's to put the marker
     * @param column - column the player want to put the marker
     *
     * @return GameSession contains a response to players actions
     */
    @Override
    @Transactional
    public GameSession placeMarker(final Long gameId, final Player player, final int row, final int column) {
        Game game = findGame(gameId);

        try {
            validateMove(game, player, row, column);
        } catch (GameException e) {
            return new GameSession(player, game, e.gameErrors);
        }

        char[][] matrix = game.getMatrix();

        // Place the marker at specified position
        matrix[row][column] = player.toChar();

        boolean isVictorious = gameLogicService.isVictorious(matrix, player.toChar(), row, column);

        if (isVictorious) {
            game.victory();
        } else {
            int maximumMoves = matrix.length * matrix.length;

            if (game.getMovesCount() == maximumMoves) {
                game.tie();
            } else {
                game.rotatePlayer();
            }
        }

        return new GameSession(player, gameRepository.saveAndFlush(game), null);
    }

    private Game createNewGame() {
        return gameRepository.saveAndFlush(new Game());
    }

    private Game startGame(final Game game) {
        game.setStatus(GameStatus.ACTIVE);
        return gameRepository.saveAndFlush(game);
    }

    private Game findGame(final Long gameId) {
        return gameRepository.
                findById(gameId).
                orElseThrow(() -> new IllegalStateException("Game not found"));
    }

    private void validateMove(final Game game, final Player player, final int row, final int column) throws GameException {

        switch (game.getStatus()) {
            case WAITING:
                throw new GameException(GameErrors.INCORRECT_GAME_STATE_NEED_TO_WAIT);
            case FINISHED:
                throw new GameException(GameErrors.INCORRECT_GAME_STATE_GAME_FINISHED);
            case ACTIVE:
                break;
        }

        char[][] matrix = game.getMatrix();

        int dimensions = matrix.length;

        if (row < 0 || row >= dimensions || column < 0 || column >= dimensions) {
            throw new GameException(GameErrors.INCORRECT_ROW_OR_COLUMN);
        }

        if (game.getCurrentPlayer() != player) {
            throw new GameException(GameErrors.INCORRECT_TURN);
        }

        if (matrix[row][column] != EMPTY_MATRIX_POSITION) {
            throw new GameException(GameErrors.POSITION_ALREADY_TAKEN);
        }
    }

}
