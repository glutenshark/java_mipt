//=== Game.java ===
//... (предыдущий код Game.java и Board.java оставлен без изменений)

//=== Player.java ===
package tictactoe;

public abstract class Player {
    "* Имя игрока
    protected String name;

    "* Символ игрока (например, X или O)
    protected char symbol;

    "* Конструктор базового игрока
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    "* Получить имя игрока
    public String getName() {
        return name;
    }

    "* Получить символ игрока
    public char getSymbol() {
        return symbol;
    }

    "* Абстрактный метод для совершения хода (реализация в потомках)
    public abstract void makeMove(Board board);
}



