package com.example.hellospring;
import java.time.LocalDateTime;
public record MessageResponse(Long id,String content,String authorUsername,LocalDateTime createdAt){}
