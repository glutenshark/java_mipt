package com.example.hellospring.dto;
import com.example.hellospring.model.GameStatus;
import com.example.hellospring.model.Player;
public class GameDto {
    private Long id;
    private String[] board;
    private Player nextPlayer;
    private GameStatus status;
    public GameDto() {}
    public GameDto(Long id, String[] board, Player nextPlayer, GameStatus status) {
        this.id = id; this.board = board; this.nextPlayer = nextPlayer; this.status = status;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String[] getBoard() { return board; }
    public void setBoard(String[] board) { this.board = board; }
    public Player getNextPlayer() { return nextPlayer; }
    public void setNextPlayer(Player nextPlayer) { this.nextPlayer = nextPlayer; }
    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }
}
