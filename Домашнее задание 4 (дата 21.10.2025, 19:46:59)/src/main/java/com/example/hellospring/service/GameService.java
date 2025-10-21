package com.example.hellospring.service;
import com.example.hellospring.dto.GameDto;
import com.example.hellospring.dto.MoveRequest;
import com.example.hellospring.exception.BadRequestException;
import com.example.hellospring.exception.NotFoundException;
import com.example.hellospring.model.Game;
import com.example.hellospring.model.GameStatus;
import com.example.hellospring.model.Player;
import com.example.hellospring.repository.GameRepository;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;
@Service
public class GameService {
    private static final int DEFAULT_LIMIT = 30;
    private static final int[][] WIN_LINES = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };
    private final GameRepository repository;
    private final Map<Long, Lock> locks = new ConcurrentHashMap<>();
    public GameService(GameRepository repository) { this.repository = repository; }
    public GameDto create() { Game g = new Game(); repository.save(g); return toDto(g); }
    public GameDto state(Long id) {
        Game g = repository.findById(id).orElseThrow(() -> new NotFoundException("Game " + id + " not found"));
        return toDto(g);
    }
    public List<GameDto> list(Integer limit) {
        int l = (limit == null || limit <= 0) ? DEFAULT_LIMIT : Math.min(limit, DEFAULT_LIMIT);
        return repository.findAllAscending(l).stream().map(this::toDto).toList();
    }
    public GameDto move(Long id, MoveRequest req) {
        Game g = repository.findById(id).orElseThrow(() -> new NotFoundException("Game " + id + " not found"));
        Lock lock = locks.computeIfAbsent(id, i -> new ReentrantLock());
        lock.lock();
        try {
            if (g.getStatus() != GameStatus.IN_PROGRESS) throw new BadRequestException("Game is already finished: " + g.getStatus());
            int idx = index(req.getX(), req.getY());
            if (g.getCells()[idx] != ' ') throw new BadRequestException("Cell (" + req.getX() + "," + req.getY() + ") is already occupied");
            char mark = g.getNextPlayer() == Player.X ? 'X' : 'O';
            g.getCells()[idx] = mark;
            GameStatus st = computeStatus(g.getCells());
            g.setStatus(st);
            if (st == GameStatus.IN_PROGRESS) g.setNextPlayer(g.getNextPlayer().other());
            g.setUpdatedAt(Instant.now());
            repository.save(g);
            return toDto(g);
        } finally { lock.unlock(); }
    }
    public void clear() { repository.clear(); }
    private static int index(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) throw new BadRequestException("Coordinates must be in [0..2]");
        return y * 3 + x;
    }
    private static GameStatus computeStatus(char[] c) {
        for (int[] line : WIN_LINES) {
            char a = c[line[0]], b = c[line[1]], d = c[line[2]];
            if (a != ' ' && a == b && b == d) return a == 'X' ? GameStatus.X_WON : GameStatus.O_WON;
        }
        for (char ch : c) if (ch == ' ') return GameStatus.IN_PROGRESS;
        return GameStatus.DRAW;
    }
    private GameDto toDto(Game g) {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) board[i] = g.getCells()[i] == ' ' ? " " : String.valueOf(g.getCells()[i]);
        return new GameDto(g.getId(), board, g.getNextPlayer(), g.getStatus());
    }
}
