package com.weigel.tomas.aboutfun.tictactoe.game.service.impl;

import com.weigel.tomas.aboutfun.tictactoe.game.service.IGameVictoryValidator;
import com.weigel.tomas.aboutfun.tictactoe.game.service.impl.victory.IVictoryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameVictoryValidator implements IGameVictoryValidator {

    @Autowired
    private List<IVictoryStrategy> victoryStrategies;

    @Override
    public boolean isVictorious(char[][] matrix, char player, int lastRow, int lastCol) {
        int dimensions = matrix.length;

        return victoryStrategies.stream().anyMatch(strategy -> {
            for (int i = 0; i < dimensions; i++) {
                int row = strategy.getRow(i, lastRow);
                int column = strategy.getColumn(i, lastCol, dimensions);

                if (matrix[row][column] != player) {
                    break;
                }

                if (i == (dimensions - 1)) {
                    return true;
                }
            }

            return false;
        });
    }

}
