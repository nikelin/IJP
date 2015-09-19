package com.udacity.connect4game.strategy;

import com.udacity.connect4game.Agent;
import com.udacity.connect4game.Connect4Game;

public interface AgentStrategy {

    int findWinMove(Connect4Game game, Agent agent);

}
