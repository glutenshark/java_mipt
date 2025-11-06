package com.example.hellospring;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service public class MessageService {
 private final MessageRepository m; private final UserRepository u;
 public MessageService(MessageRepository m,UserRepository u){this.m=m;this.u=u;}
 @Transactional public Message saveMessage(String c,String n){User a=u.findByUsername(n).orElseGet(()->u.save(new User(n)));return m.save(new Message(c,a));}
 @Transactional(readOnly=true) public List<Message> getLatestMessagesNPlus1(){List<Message> msgs=m.findTop30ByOrderByCreatedAtDesc();msgs.forEach(x->x.getAuthor().getUsername());return msgs;}
 @Transactional(readOnly=true) public List<Message> getLatestMessagesOptimized(){return m.findLatestWithAuthors(PageRequest.of(0,30));}
 @Transactional(readOnly=true) public List<Message> getLatestMessagesOptimizedEntityGraph(){return m.findByOrderByCreatedAtDesc(PageRequest.of(0,30)).getContent();}
 public Message getMessageToFail(Long id){return m.findById(id).orElseThrow();}
 @Transactional(readOnly=true) public MessageResponse getMessageOk(Long id){Message x=m.findById(id).orElseThrow();return new MessageResponse(x.getId(),x.getContent(),x.getAuthor().getUsername(),x.getCreatedAt());}
}
