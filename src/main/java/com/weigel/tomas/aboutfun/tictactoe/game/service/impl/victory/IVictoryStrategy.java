package com.weigel.tomas.aboutfun.tictactoe.game.service.impl.victory;

import org.springframework.stereotype.Component;

@Component
public interface IVictoryStrategy {

    int getRow(int iterator, int lastRow);
    int getColumn(int iterator, int lastColumn, int dimensions);

}

