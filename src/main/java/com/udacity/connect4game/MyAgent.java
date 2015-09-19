package com.udacity.connect4game;

import com.udacity.connect4game.strategy.AgentStrategy;

public class MyAgent extends Agent {
    private final AgentStrategy strategy;

    /**
     * @param game Reference on current game
     * @param iAmRed Is this player red
     */
    public MyAgent(AgentStrategy strategy, Connect4Game game, boolean iAmRed) {
        super(game, iAmRed);

        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return "MyAgent(" + this.strategy.getClass().getSimpleName() + ")";
    }

    @Override
    public void move() {
        Connect4GameUtils.occupySlot(myGame, getColor(), strategy.findWinMove(myGame, this));
    }
}
