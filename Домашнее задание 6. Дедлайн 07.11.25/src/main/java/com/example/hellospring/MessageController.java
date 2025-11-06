package com.example.hellospring;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
record CreateMessageRequest(String content,String username){}
@RestController @RequestMapping("/api/messages")
public class MessageController {
 private final MessageService s; public MessageController(MessageService s){this.s=s;}
 @GetMapping("/optimized") public List<Message> o(){return s.getLatestMessagesOptimized();}
 @GetMapping("/optimized-entitygraph") public List<Message> e(){return s.getLatestMessagesOptimizedEntityGraph();}
 @GetMapping("/nplus1") public List<Message> n(){return s.getLatestMessagesNPlus1();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Message post(@RequestBody CreateMessageRequest r){String n=(r.username()==null||r.username().isBlank())?"Anonymous":r.username().trim();return s.saveMessage(r.content(),n);}
 @GetMapping("/fail/{id}") public ResponseEntity<?> fail(@PathVariable Long id){try{return ResponseEntity.ok(s.getMessageToFail(id));}catch(Exception e){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ожидаемая ошибка LazyInitializationException. Используйте /api/messages/{id}");}}
 @GetMapping("/{id}") public MessageResponse ok(@PathVariable Long id){return s.getMessageOk(id);}
}
