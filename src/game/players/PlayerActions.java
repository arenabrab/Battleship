package game.players;

import game.board.Position;

public interface PlayerActions {
    Position.Status tryStrike(Character row, Integer column);
    boolean allShipsSunk();
    void updateGrid(char row, int column, Position.Status status);
    Position findRandomFreePosition();
    Position getPosition(Character row, Integer column);
    void takeTurn(Character row, Integer column, Player opponent);
}
