package com.example.hellospring.controller;
import com.example.hellospring.dto.GameDto;
import com.example.hellospring.dto.MoveRequest;
import com.example.hellospring.service.GameService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService service;
    public GameController(GameService service) { this.service = service; }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto create() { return service.create(); }
    @GetMapping("/{id}")
    public GameDto state(@PathVariable Long id) { return service.state(id); }
    @GetMapping
    public List<GameDto> list(@RequestParam(name = "limit", required = false) Integer limit) { return service.list(limit); }
    @PostMapping("/{id}/moves")
    public GameDto move(@PathVariable Long id, @Valid @RequestBody MoveRequest req) { return service.move(id, req); }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() { service.clear(); }
}
