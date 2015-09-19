package com.udacity.connect4game;

public class Connect4GameUtils {

    public static boolean hasWinner(Connect4Game game) {
        return game.gameWon() != Connect4Game.NO_PAYER;
    }

    public static boolean isValidMove(Connect4Game myGame, int column) {
        return !myGame.getColumn(column).getIsFull();
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     *
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public static int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }

    public static int occupySlot(Connect4Game gameObject, char color, int columnNumber) {
        int lowestEmptySlotIndex = getLowestEmptyIndex(gameObject.getColumn(columnNumber));   // Find the top empty slot in the column
        // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = gameObject.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (color == Connect4Game.RED) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }

        return lowestEmptySlotIndex;
    }

}
