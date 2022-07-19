package game.board;

import java.util.*;
import java.util.function.BiPredicate;

public class Grid {
    private final Map<Position, Position.Status> grid = new HashMap<>();
    private final Random random = new Random();

    public Grid() {
        createNewGrid();
    }

    public void setPositionStatus(Character row, Integer column, Position.Status status) {
        this.grid.put(findPosition(row, column), status);
    }

    public Position findPosition(Character row, Integer column) {
        return this.grid.keySet().stream()
                .filter(status -> byColumn.test(status, column))
                .filter(status -> byRow.test(status, row))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Did not find position"));
    }

    public Position findFreePosition(Character row, Integer column) {
        return this.grid.entrySet().stream()
                .filter(entry -> entry.getValue().equals(Position.Status.FREE))
                .filter(entry -> byRow.test(entry.getKey(), row))
                .filter(entry -> byColumn.test(entry.getKey(), column))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No free position at " + row + column));
    }

    public Set<Position> findFreeSubSet(int shipLength) {
        Set<Position> horizSet = new HashSet<>();
        Set<Position> vertSet = new HashSet<>();

        do {
            horizSet.clear();
            vertSet.clear();
            try {
                Position startingPosition = findRandomFreePosition();
                for (int i = 0; i < shipLength; i++) {
                    horizSet.add(findPosition(startingPosition.getRow(), startingPosition.getColumn() + i));
                    vertSet.add(findPosition((char) (startingPosition.getRow() + i), startingPosition.getColumn()));
                }
            } catch (RuntimeException e) {
                // no-op
            }
        } while(setNotValid.test(horizSet, shipLength) || setNotValid.test(vertSet, shipLength));

        return random.nextBoolean() ? vertSet : horizSet;
    }

    public Position findRandomPosition() {
        Character randomRow = (char) ('A' + random.nextInt(0, 10));
        Integer randomColumn = 1 + random.nextInt(0, 10);
        return findPosition(randomRow, randomColumn);
    }

    private Position findRandomFreePosition() {
        Character randomRow = (char) ('A' + random.nextInt(0, 10));
        Integer randomColumn = 1 + random.nextInt(0, 10);
        return findFreePosition(randomRow, randomColumn);
    }

    private void createNewGrid() {
        for(int column = 1; column <= 10; column++) {
            for(char row = 'A'; row <= 'J'; row++) {
                this.grid.put(new Position(row, column), Position.Status.FREE);
            }
        }
    }

    private final BiPredicate<Set<Position>, Integer> setNotValid = (set, shipLength) -> set.isEmpty() || set.size() != shipLength || set.stream().anyMatch(position -> grid.get(position).equals(Position.Status.TAKEN));
    private final BiPredicate<Position, Character> byRow = (pos, row) -> pos.getRow().equals(row);
    private final BiPredicate<Position, Integer> byColumn = (pos, col) -> pos.getColumn().equals(col);

}
