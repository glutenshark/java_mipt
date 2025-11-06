package com.example.hellospring;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
@Entity @Table(name="app_user") @JsonIgnoreProperties({"messages"})
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String username;
 @OneToMany(mappedBy="author",cascade=CascadeType.ALL,orphanRemoval=true) private List<Message> messages;
 public User(){} public User(String username){this.username=username;}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getUsername(){return username;} public void setUsername(String username){this.username=username;}
 public List<Message> getMessages(){return messages;} public void setMessages(List<Message> m){this.messages=m;}
 public String toString(){return "User{id="+id+", username="+username+"}";}
}
