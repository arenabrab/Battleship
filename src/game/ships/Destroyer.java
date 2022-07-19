package game.ships;

import game.board.Position;

import java.util.Set;

import static java.lang.System.out;

public class Destroyer extends Ship {
    public Destroyer(Set<Position> positions) {
        super(positions);
    }

    public void printIfShipSunk() {
        boolean shipIsSunk = getPositionStatuses().stream().allMatch(Position.Status.HIT::equals);
        if(shipIsSunk) {
            setToSunk();
            out.printf("%s is sunk!%n", Destroyer.class.getSimpleName());
        }
    }
}
