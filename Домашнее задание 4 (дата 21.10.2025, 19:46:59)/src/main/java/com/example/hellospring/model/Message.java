package com.example.hellospring.model;
import java.time.Instant;
import java.util.Objects;
public class Message {
    private Long id;
    private String author;
    private String content;
    private Instant createdAt;
    public Message() {}
    public Message(String author, String content) {
        this.author = author; this.content = content; this.createdAt = Instant.now();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Message)) return false; Message m=(Message)o; return Objects.equals(id,m.id); }
    @Override public int hashCode() { return Objects.hash(id); }
}
