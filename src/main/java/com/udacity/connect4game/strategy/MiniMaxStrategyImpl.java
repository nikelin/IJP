package com.udacity.connect4game.strategy;

import com.udacity.connect4game.Agent;
import com.udacity.connect4game.Connect4Game;
import com.udacity.connect4game.Connect4GameUtils;

/**
 * Custom agent implementation based on Minimax strategy, which means
 * that current agent tries to maximize the revenue of its turn based on the assumption, that Min will do
 * the exact opposite and minimize Max’s revenue
 *
 * So Max, while thinking several steps ahead, will always choose the next step to be that one, where even if Min
 * plays perfectly his revenue is still maximized.
 *
 * As you might see the algorithm produces a tree that has to be searched for the best option. The root node of the tree
 * would be an empty board, assuming one that has seven columns, branching into seven states, that represent all
 * the possible first actions.
 *
 * @author Cyril A. Karpenko
 * Note:
 */
public class MiniMaxStrategyImpl implements AgentStrategy {

    /**
     * Amount of steps ahead which AI could think about
     */
    static final int MAX_DEPTH = 8;

    /**
     * The score given to a state that leads to a
     * win.
     */
    static final float WIN_REVENUE = 1f;

    /**
     * The score given to a state that leads to a
     * lose.
     */
    static final float LOSE_REVENUE = -1f;

    /**
     * The score given to a state that leads to a
     * loss in the next turn
     */
    static final float UNCERTAIN_REVENUE = 0f;

    @Override
    public int findWinMove(Connect4Game game, Agent agent) {
        double maxValue = 2. * Integer.MIN_VALUE;
        int move = 0;

        for (int column = 0; column < game.getRowCount(); column++) {
            if (Connect4GameUtils.isValidMove(game, column)) {
                double value = moveCost(game, agent, column);
                if (value > maxValue) {
                    maxValue = value;
                    move = column;
                    if (value == WIN_REVENUE) {
                        break;
                    }
                }
            }
        }

        return move;
    }

    /**
     * Estimate cost (revenue) in a case of occupying given column by the given agent
     * @param game Reference to the game object
     * @param agent Acting agent reference
     * @param column Estimation subject
     * @return Score
     */
    protected double moveCost(Connect4Game game, Agent agent, int column) {
        int slot = Connect4GameUtils.occupySlot(game, agent.getColor(), column);

        double val = alphaBeta(game, agent, MAX_DEPTH,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE, false);

        game.getColumn(column).getSlot(slot).clear();

        return val;
    }

    private double alphaBeta(Connect4Game game, Agent agent,
                             int depth, double alphaValue, double betaValue,
                             boolean isMaximizingPlayer) {
        boolean hasWinner = Connect4GameUtils.hasWinner(game);

        double result;
        if (depth == 0 || hasWinner) {
            double score = 0;
            if (hasWinner) {
                score = game.gameWon() != agent.getColor() ? LOSE_REVENUE : WIN_REVENUE;
            } else {
                score = UNCERTAIN_REVENUE;
            }

            result = score / (MAX_DEPTH - depth + 1);
        } else if ( isMaximizingPlayer ) {
            for (int column = 0; column < game.getColumnCount(); column++) {
                if (Connect4GameUtils.isValidMove(game, column)) {
                    int slot = Connect4GameUtils.occupySlot(game, agent.getColor(), column);

                    alphaValue = Math.max(
                            alphaValue,
                            alphaBeta( game, agent,
                                    depth - 1, alphaValue, betaValue, false
                            )
                    );

                    game.getColumn(column).getSlot(slot).clear();

                    if (betaValue <= alphaValue) {
                        break;
                    }
                }
            }

            result = alphaValue;
        } else {
            for (int column = 0; column < game.getColumnCount(); column++) {
                if (Connect4GameUtils.isValidMove(game, column)) {
                    int slot = Connect4GameUtils.occupySlot(game, agent.getOpponentColor(), column);

                    betaValue = Math.min(
                            betaValue,
                            alphaBeta(game, agent, depth - 1,
                                    alphaValue,
                                    betaValue,
                                    true));

                    game.getColumn(column).getSlot(slot).clear();

                    if (betaValue <= alphaValue) {
                        break;
                    }
                }
            }

            result = betaValue;
        }

        return result;
    }


}
