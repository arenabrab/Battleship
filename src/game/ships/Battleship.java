package game.ships;

import game.board.Position;

import java.util.Set;

import static java.lang.System.*;

public class Battleship extends Ship {
    public Battleship(Set<Position> positions) {
        super(positions);
    }

    public void printIfShipSunk() {
        boolean shipIsSunk = getPositionStatuses().stream().allMatch(Position.Status.HIT::equals);
        if(shipIsSunk) {
            setToSunk();
            out.printf("%s is sunk!%n", Battleship.class.getSimpleName());
        }
    }
}
