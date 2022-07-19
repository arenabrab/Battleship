package game.players;

import game.board.Position;
import game.ships.Ship;

import static java.lang.System.out;

public class Computer extends Player {
    public Computer() {
        super();
    }

    @Override
    public void takeTurn(Character row, Integer column, Player player) {
        out.printf("Computer selects %c%d%n", row, column);
        Position.Status status = player.tryStrike(row, column);
        out.printf("%c%d is a %s%n", row, column, status);
        player.getShips().forEach(Ship::printIfShipSunk);
        this.updateGrid(row, column, status);
    }

}
