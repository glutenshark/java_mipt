package com.example.hellospring;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component public class DataInitializer {
 private final UserRepository u; private final MessageRepository m;
 public DataInitializer(UserRepository u,MessageRepository m){this.u=u;this.m=m;}
 @EventListener(ApplicationReadyEvent.class) @Transactional public void init(){
  if(m.count()>0)return;User a=u.save(new User("Alice"));User b=u.save(new User("Bob"));
  m.save(new Message("Hello, JPA and Hibernate!",a));m.save(new Message("This uses Spring Data JPA interface.",b));m.save(new Message("Relationships are now managed by ORM.",a));}}
