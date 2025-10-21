package com.example.hellospring.exception;
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
