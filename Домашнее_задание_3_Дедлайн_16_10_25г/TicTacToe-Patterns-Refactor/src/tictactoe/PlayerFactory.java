package tictactoe;

public class PlayerFactory {
    "* Фабричный метод создания игрока по типу
    public static Player createPlayer(String type, String name, char symbol) {
        if (type.equalsIgnoreCase("AI")) {
            return new AIPlayer(name, symbol);
        } else {
            return new HumanPlayer(name, symbol);
        }
    }
}
