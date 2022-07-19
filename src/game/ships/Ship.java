package game.ships;

import game.board.Position;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public abstract class Ship {
    private final Map<Position, Position.Status> positions;

    protected Ship(Set<Position> positions) {
        this.positions = setNewPositions(positions);
    }

    public Collection<Position.Status> getPositionStatuses() {
        return positions.values();
    }

    public Position.Status tryStrike(Position position) {
        return positions.entrySet().stream().anyMatch(entry -> entry.getKey().equals(position)) ? setPosition(position) : Position.Status.MISS;
    }

    private Map<Position, Position.Status> setNewPositions(Set<Position> positions) {
        return positions.stream()
                .collect(Collectors.toMap(Function.identity(), pos -> Position.Status.TAKEN));
    }

    private Position.Status setPosition(Position position) {
        if(!positions.get(position).equals(Position.Status.SUNK)) {
            this.positions.put(position, Position.Status.HIT);
        }
        return Position.Status.HIT;
    }

    public void setToSunk() {
        positions.entrySet().forEach(entry -> entry.setValue(Position.Status.SUNK));
    }

    public abstract void printIfShipSunk();


}
