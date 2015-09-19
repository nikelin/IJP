package com.udacity.connect4game;

import com.sun.glass.ui.SystemClipboard;
import com.udacity.connect4game.strategy.AgentStrategy;
import com.udacity.connect4game.strategy.MiniMaxStrategyImpl;
import com.udacity.connect4game.udacityAgents.AdvancedAgent;
import com.udacity.connect4game.udacityAgents.BeginnerAgent;
import com.udacity.connect4game.udacityAgents.BrilliantAgent;
import com.udacity.connect4game.udacityAgents.RandomAgent;

public class TestCases {

    private static int yellowWins = 0;
    private static int redWins = 0;
    private static int dry = 0;

    private static char play(Connect4Game game, Agent redPlayer, Agent yellowPlayer) {
        boolean redPlayerTurn = game.getRedPlayedFirst();
        boolean gameActive = true;
        while ( gameActive && !Connect4GameUtils.hasWinner(game) ) {
            Connect4Game oldBoard = new Connect4Game(game);   // store the old board for validation
            if(redPlayerTurn) {
                redPlayer.move();
            } else {
                yellowPlayer.move();
            }

            String validateResult = oldBoard.validate(game);
            if(validateResult.length() > 0) {
                gameActive = false;
            }

            redPlayerTurn = !redPlayerTurn;   // switch whose turn it is
        }

        char winnerCode = game.gameWon();
        game.clearBoard();
        return winnerCode;
    }

    private static void playFor(Connect4Game game, Agent redPlayer, Agent yellowPlayer, final int attempts ) {
        System.out.println("Starting game R=" + redPlayer.getName() + " vs Y=" + yellowPlayer.getName() );
        System.out.println("Playing out " + attempts + " attempts");
        for ( int i = attempts; i > 0; i-- ) {
            char winnerCode = play(game, redPlayer, yellowPlayer);
            switch ( winnerCode ) {
                case Connect4Game.YELLOW:
                    yellowWins += 1;
                break;
                case Connect4Game.RED:
                    redWins += 1;
                break;
                case Connect4Game.NO_PAYER:
                    dry += 1;
            }

            System.out.println( winnerCode + " has won!");

            game.clearBoard();
        }
    }

    public static void main(String[] args) {
        playMiniMaxVsRandom();
        playMiniMaxVsBeginner();
        playMiniMaxVsBrilliant();
        playMiniMaxVsAdvanced();
    }

    private static void playMiniMaxVsRandom() {
        Connect4Game game = new Connect4Game(7, 6);

        playFor(
            game,
            newCustomAgent(new MiniMaxStrategyImpl(), game, true),
            newRandomAgent(game, false),
            20
        );

        printReportAndReset("(MiniMax/Random)");
    }

    private static void playMiniMaxVsBeginner() {
        Connect4Game game = new Connect4Game(7, 6);

        playFor(
            game,
            newCustomAgent(new MiniMaxStrategyImpl(), game, true),
            newBeginnerAgent(game, false),
            20
        );

        printReportAndReset("(MiniMax/Beginner)");
    }

    private static void playMiniMaxVsAdvanced() {
        Connect4Game game = new Connect4Game(7, 6);
        playFor(
            game,
            newCustomAgent(new MiniMaxStrategyImpl(), game, true),
            newAdvancedAgent(game, false),
            20
        );

        printReportAndReset("(MiniMax/Advanced)");
    }

    private static void playMiniMaxVsBrilliant() {
        Connect4Game game = new Connect4Game(7, 6);
        playFor(
                game,
                newCustomAgent(new MiniMaxStrategyImpl(), game, true),
                newBrilliantAgent(game, false),
                20
        );

        printReportAndReset("(MiniMax/Brilliant)");
    }

    private static void printReportAndReset(String details) {
        printReport(details);
        dry = redWins = yellowWins = 0;
    }

    private static void printReport(String details) {
        System.out.println(details + " R - " + redWins + "; Y - " + yellowWins + "; Dry - " + dry );
    }

    private static Agent newCustomAgent(AgentStrategy strategy,
                                        Connect4Game game, boolean isRed) {
        return new MyAgent(strategy, game, isRed);
    }

    private static Agent newBrilliantAgent(Connect4Game game, boolean isRed) {
        return new BrilliantAgent(game, isRed);
    }

    private static Agent newAdvancedAgent(Connect4Game game, boolean isRed) {
        return new AdvancedAgent(game, isRed);
    }

    private static Agent newBeginnerAgent(Connect4Game game, boolean isRed) {
        return new BeginnerAgent(game, isRed);
    }

    private static Agent newRandomAgent(Connect4Game game, boolean isRed) {
        return new RandomAgent(game, isRed);
    }

}
