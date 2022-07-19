package game.board;

public class Position {
    public enum Status {
        FREE, TAKEN, HIT, MISS, SUNK
    }

    private final Character row;
    private final Integer column;

    public Position(Character row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Character getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
}
