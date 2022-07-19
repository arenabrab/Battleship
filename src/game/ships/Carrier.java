package game.ships;

import game.board.Position;

import java.util.Set;

import static java.lang.System.out;

public class Carrier extends Ship {
    public Carrier(Set<Position> positions) {
        super(positions);
    }

    public void printIfShipSunk() {
        boolean shipIsSunk = getPositionStatuses().stream().allMatch(Position.Status.HIT::equals);
        if(shipIsSunk) {
            setToSunk();
            out.printf("%s is sunk!%n", Carrier.class.getSimpleName());
        }
    }
}
