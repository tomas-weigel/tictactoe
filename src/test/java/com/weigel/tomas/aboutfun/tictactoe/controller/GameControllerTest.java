package com.weigel.tomas.aboutfun.tictactoe.controller;

import com.weigel.tomas.aboutfun.tictactoe.game.enums.GameStatus;
import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHappyPath() throws Exception {
        joinBattle(Player.X);
        joinBattle(Player.O);

        placeMarker(Player.X, GameStatus.ACTIVE, null,1L, 0, 0);
        placeMarker(Player.O, GameStatus.ACTIVE, null,1L, 1, 0);

        placeMarker(Player.X, GameStatus.ACTIVE, null,1L, 0, 1);
        placeMarker(Player.O, GameStatus.ACTIVE, null,1L, 1, 1);

        placeMarker(Player.X, GameStatus.FINISHED, Player.X, 1L, 0, 2);
    }

    private void joinBattle(Player expectedPlayer) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/game/joinBattle")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player", is(expectedPlayer.toString())));
    }

    private void placeMarker(Player expectedPlayer, GameStatus expectedGameStatus, Player expectedWinner, Long gameId, int row, int col) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/game/" + gameId + "/placeMarker/" + expectedPlayer + "/" + row + "/" + col)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player", is(expectedPlayer.toString())))
                .andExpect(jsonPath("$.game.status", is(expectedGameStatus.toString())));
    }

}
