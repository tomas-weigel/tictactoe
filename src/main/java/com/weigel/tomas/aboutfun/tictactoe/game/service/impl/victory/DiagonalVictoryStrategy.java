package com.weigel.tomas.aboutfun.tictactoe.game.service.impl.victory;

import org.springframework.stereotype.Component;

@Component
public class DiagonalVictoryStrategy implements IVictoryStrategy {

    @Override
    public int getRow(int iterator, int lastRow) {
        return iterator;
    }

    @Override
    public int getColumn(int iterator, int lastColumn, int dimensions) {
        return iterator;
    }

}
