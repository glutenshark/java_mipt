package com.example.hellospring.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class NewMessageRequest {
    @NotBlank @Size(max = 64)
    private String author;
    @NotBlank @Size(max = 2048)
    private String content;
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
