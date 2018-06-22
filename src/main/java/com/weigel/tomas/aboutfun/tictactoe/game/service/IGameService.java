package com.weigel.tomas.aboutfun.tictactoe.game.service;

import com.weigel.tomas.aboutfun.tictactoe.game.dto.GameSession;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;
import org.springframework.transaction.annotation.Transactional;

public interface IGameService {

    /**
     * Players will join a match, if there is one created or will create new one,
     * if there isn’t anyone waiting for another player
     *
     * @return GameSession contains a response to players actions
     */
    @Transactional
    GameSession joinBattle();

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
    @Transactional
    GameSession placeMarker(Long gameId, Player player, int row, int column);
}
