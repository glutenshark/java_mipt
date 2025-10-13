package tictactoe;

public class Board {
    * Размер поля
    private int size;

    * Двумерный массив для клеток поля
    private char[][] cells;

    * Символ пустой клетки
    private char emptyChar;

    * Приватный конструктор — используется Builder
    private Board(int size, char emptyChar) {
        this.size = size;
        this.emptyChar = emptyChar;
        this.cells = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = emptyChar;
            }
        }
    }

    * Отображение игрового поля в консоли
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j]);
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    * Проверка: заполнено ли всё поле
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == emptyChar) {
                    return false;
                }
            }
        }
        return true;
    }

    * Проверка победителя по символу
    public boolean hasWin(char symbol) {
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < size; j++) {
                if (cells[i][j] != symbol) rowWin = false;
                if (cells[j][i] != symbol) colWin = false;
            }
            if (rowWin || colWin) return true;
        }

        boolean diag1 = true;
        boolean diag2 = true;
        for (int i = 0; i < size; i++) {
            if (cells[i][i] != symbol) diag1 = false;
            if (cells[i][size - 1 - i] != symbol) diag2 = false;
        }
        return diag1 || diag2;
    }

    * Попытка поставить символ в ячейку
    public boolean placeSymbol(int row, int col, char symbol) {
        if (cells[row][col] == emptyChar) {
            cells[row][col] = symbol;
            return true;
        }
        return false;
    }

    * Получение размера поля
    public int getSize() {
        return size;
    }

    * Класс Builder для пошагового создания Board
    public static class Builder {
        private int size = 3;
        private char emptyChar = '-';

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setEmptyChar(char emptyChar) {
            this.emptyChar = emptyChar;
            return this;
        }

        public Board build() {
            return new Board(size, emptyChar);
        }
    }
}
