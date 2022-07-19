package game;

import game.board.Position;
import game.players.Computer;
import game.players.Human;
import game.players.Player;
import game.ships.Ship;

import java.util.Scanner;

import static java.lang.System.out;

public class Game {
    private final Human human;
    private final Computer computer;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        this.human = new Human();
        this.computer = new Computer();
    }

    public void playGame() {
        do {
            try {
                humanTurn();
                computerTurn();
            } catch (Exception e) {
                out.println("Please input a valid coordinate");
            }
        } while (!noWinnerYet());
        String winner = human.allShipsSunk() ? Computer.class.getSimpleName() : Human.class.getSimpleName();
        out.println(winner + " is the winner!");
    }

    private boolean noWinnerYet() {
        return human.allShipsSunk() || computer.allShipsSunk();
    }

    private void humanTurn() {
        out.println("Select a square on the grid: ");
        String move = scanner.nextLine();
        char row = move.toUpperCase().charAt(0);
        int column = Integer.parseInt(move.substring(1));

        human.takeTurn(row, column, computer);
    }

    private void computerTurn() {
        Position position = computer.findRandomFreePosition();
        computer.takeTurn(position.getRow(), position.getColumn(), human);
    }
}
