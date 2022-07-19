package game.players;

import game.board.Position;
import game.ships.Ship;

import static java.lang.System.out;

public class Human extends Player {
    public Human() {
        super();
    }

    @Override
    public void takeTurn(Character row, Integer column, Player player) {
        Position.Status status = player.tryStrike(row, column);
        out.printf("%c%d is a %s%n", row, column, status);
        player.getShips().forEach(Ship::printIfShipSunk);
        this.updateGrid(row, column, status);
    }
}
