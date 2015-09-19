package com.udacity.connect4game.strategy;

import com.udacity.connect4game.Agent;
import com.udacity.connect4game.Connect4Game;
import com.udacity.connect4game.Connect4GameUtils;

import java.util.Random;

public class RandomStrategyImpl implements AgentStrategy {
    private final Random random = new Random();

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     *
     * @return a random valid move.
     */
    @Override
    public int findWinMove(Connect4Game game, Agent agent)
    {
        int i = random.nextInt(game.getColumnCount());
        while (Connect4GameUtils.getLowestEmptyIndex(game.getColumn(i)) == -1)
        {
            i = random.nextInt(game.getColumnCount());
        }
        return i;
    }

}
