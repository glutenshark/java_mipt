package com.example.hellospring.repository;
import com.example.hellospring.model.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
@Repository
public class GameRepository {
    private final ConcurrentMap<Long, Game> storage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);
    public Game save(Game game) {
        if (game.getId() == null) { long id = idCounter.incrementAndGet(); game.setId(id); }
        storage.put(game.getId(), game);
        return game;
    }
    public Optional<Game> findById(Long id) { return Optional.ofNullable(storage.get(id)); }
    public List<Game> findAllAscending(int limit) {
        List<Long> ids = new ArrayList<>(storage.keySet());
        Collections.sort(ids);
        List<Game> result = new ArrayList<>();
        int start = Math.max(0, ids.size() - limit);
        for (int i = start; i < ids.size(); i++) result.add(storage.get(ids.get(i)));
        return result;
    }
    public void deleteById(Long id) { storage.remove(id); }
    public void clear() { storage.clear(); }
}
