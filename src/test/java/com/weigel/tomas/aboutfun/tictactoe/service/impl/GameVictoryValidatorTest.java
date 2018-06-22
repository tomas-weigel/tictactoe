package com.weigel.tomas.aboutfun.tictactoe.service.impl;

import com.weigel.tomas.aboutfun.tictactoe.game.enums.Player;
import com.weigel.tomas.aboutfun.tictactoe.game.service.IGameVictoryValidator;
import com.weigel.tomas.aboutfun.tictactoe.game.service.impl.GameVictoryValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameVictoryValidatorTest {

    @Autowired
    IGameVictoryValidator gameVictoryValidator;

    @Test
    public void testVictoryByRow() {
        char[][] matrix = new char[][] {
                {'X', 'X', 'X'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 2);
        assertTrue(victorious);
    }

    @Test
    public void testVictoryByColumn() {
        char[][] matrix = new char[][] {
                {'X', '0', '0'},
                {'X', '0', '0'},
                {'X', '0', '0'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 0);
        assertTrue(victorious);
    }

    @Test
    public void testVictoryByDiagonal() {
        char[][] matrix = new char[][] {
                {'X', '0', '0'},
                {'0', 'X', '0'},
                {'0', '0', 'X'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 0);
        assertTrue(victorious);
    }

    @Test
    public void testVictoryByAntiDiagonal() {
        char[][] matrix = new char[][] {
                {'0', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '0', '0'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 0);
        assertTrue(victorious);
    }

    @Test
    public void testVictoryByAntiDiagonal2() {
        char[][] matrix = new char[][] {
                {'0', '0', 'X'},
                {'0', 'X', '0'},
                {'X', '0', '0'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 2);
        assertTrue(victorious);
    }

    @Test
    public void testNoVictory() {
        char[][] matrix = new char[][] {
                {'O', '0', 'X'},
                {'0', 'O', '0'},
                {'0', 'X', '0'}
        };

        boolean victorious = gameVictoryValidator.isVictorious(matrix, Player.X.toChar(), 0, 0);
        assertFalse(victorious);
    }

}
