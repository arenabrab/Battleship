package game.players;

import game.board.Grid;
import game.board.Position;
import game.ships.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Player implements PlayerActions {
    private final Grid grid;
    private final Set<Ship> ships = new HashSet<>();

    Player() {
        this.grid = new Grid();
        buildAndSetShips();
    }

    private void buildAndSetShips() {
        ships.add(new PatrolBoat(this.grid.findFreeSubSet(2)));
        ships.add(new Destroyer(this.grid.findFreeSubSet(3)));
        ships.add(new Battleship(this.grid.findFreeSubSet(4)));
        ships.add(new Carrier(this.grid.findFreeSubSet(5)));
    }

    public Set<Ship> getShips() {
        return this.ships;
    }

    @Override
    public boolean allShipsSunk() {
        return getShips().stream()
                .map(Ship::getPositionStatuses)
                .flatMap(Collection::stream)
                .allMatch(Position.Status.SUNK::equals);
    }

    @Override
    public void updateGrid(char row, int column, Position.Status status) {
        this.grid.setPositionStatus(row, column, status);
    }

    @Override
    public Position findRandomFreePosition() {
        return this.grid.findRandomPosition();
    }

    @Override
    public Position.Status tryStrike(Character row, Integer column) {
        return getShips().stream().map(ship -> ship.tryStrike(getPosition(row, column)))
                .anyMatch(Position.Status.HIT::equals) ? Position.Status.HIT : Position.Status.MISS;
    }

    @Override
    public Position getPosition(Character row, Integer column) {
        return this.grid.findPosition(row, column);
    }
}
