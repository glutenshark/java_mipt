package com.example.hellospring.model;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
public class Game {
    private Long id;
    private char[] cells = new char[9];
    private Player nextPlayer = Player.X;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    public Game() { Arrays.fill(cells, ' '); }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public char[] getCells() { return cells; }
    public void setCells(char[] cells) { this.cells = cells; }
    public Player getNextPlayer() { return nextPlayer; }
    public void setNextPlayer(Player nextPlayer) { this.nextPlayer = nextPlayer; }
    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Game)) return false; Game g=(Game)o; return Objects.equals(id,g.id); }
    @Override public int hashCode() { return Objects.hash(id); }
}
