package com.example.hellospring;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="message") @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Message {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=2048) private String content;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="author_id") private User author;
 public Message(){} public Message(String c,User a){this.content=c;this.author=a;}
 @PrePersist public void pre(){if(createdAt==null)createdAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getContent(){return content;} public void setContent(String c){this.content=c;}
 public LocalDateTime getCreatedAt(){return createdAt;} public User getAuthor(){return author;} public void setAuthor(User a){this.author=a;}
 public String toString(){return "Message{id="+id+", content="+content+"}";}
}
