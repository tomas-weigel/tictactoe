package com.weigel.tomas.aboutfun.tictactoe.game.controller;

import com.weigel.tomas.aboutfun.tictactoe.game.service.impl.GameService;
import com.weigel.tomas.aboutfun.tictactoe.game.dto.GameSession;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping("/game/joinBattle")
    public ResponseEntity<GameSession> joinBattle() {

        return wrapResponse(gameService.joinBattle());
    }

    @RequestMapping("/game/{gameId}/placeMarker/{player}/{row}/{column}")
    public ResponseEntity<GameSession> placeMarker(@PathVariable Long gameId, @PathVariable Player player, @PathVariable int row, @PathVariable int column) throws Throwable {
        GameSession gameSession = gameService.placeMarker(gameId, player, row, column);

        return wrapResponse(gameSession);
    }

    private ResponseEntity<GameSession> wrapResponse(GameSession gameSession) {
        return gameSession.gameErrors
                .map((i) -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gameSession))
                .orElse(ResponseEntity.ok(gameSession));
    }

}
