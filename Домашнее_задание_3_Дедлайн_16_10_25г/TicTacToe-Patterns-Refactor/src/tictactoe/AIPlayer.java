package tictactoe;

import java.util.Random;

public class AIPlayer extends Player {
    "* Генератор случайных чисел для хода AI
    private static final Random random = new Random();

    "* Конструктор игрока-компьютера
    public AIPlayer(String name, char symbol) {
        super(name, symbol);
    }

    "* Метод случайного хода компьютера
    @Override
    public void makeMove(Board board) {
        int row, col;
        do {
            row = random.nextInt(board.getSize());
            col = random.nextInt(board.getSize());
        } while (!board.placeSymbol(row, col, symbol));

        System.out.println(name + " (" + symbol + ") сделал ход: " + (row + 1) + " " + (col + 1));
    }
}
