package tictactoe;

public class Game {
    * Экземпляр Singleton
    private static Game instance;

    * Зависимости, внедрённые через конструктор (DI)
    private Board board;
    private Player currentPlayer;
    private Player playerX;
    private Player playerO;

    * Приватный конструктор — реализация Singleton
    private Game(Board board, Player playerX, Player playerO) {
        this.board = board;
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX;
    }

    * Метод доступа к экземпляру Singleton с внедрением зависимостей
    public static Game getInstance(Board board, Player playerX, Player playerO) {
        if (instance == null) {
            instance = new Game(board, playerX, playerO);
        }
        return instance;
    }

    * Основной цикл игры
    public void start() {
        System.out.println("Игрок 1 (" + playerX.getSymbol() + ") и Игрок 2 (" + playerO.getSymbol() + ") — начинаем игру!");
        System.out.println("Игрок 1 (" + playerX.getSymbol() + ") ходит первым.");
        board.printBoard();

        while (true) {
            currentPlayer.makeMove(board);

            if (board.hasWin(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") победил!");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Ничья!");
                break;
            }

            board.printBoard();
            switchPlayer();
        }
    }

    * Смена активного игрока
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    * Точка входа: создание объектов через Builder и Factory, передача в Singleton
    public static void main(String[] args) {
        Board board = new Board.Builder().setSize(3).build();

        Player player1 = PlayerFactory.createPlayer("HUMAN", "Игрок 1", 'X');
        Player player2 = PlayerFactory.createPlayer("HUMAN", "Игрок 2", 'O');
        // Player player2 = PlayerFactory.createPlayer("AI", "Компьютер", 'O'); // ⇦ пример с AI

        Game game = Game.getInstance(board, player1, player2);
        game.start();
    }
}
