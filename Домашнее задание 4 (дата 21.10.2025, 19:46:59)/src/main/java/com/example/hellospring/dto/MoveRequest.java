package com.example.hellospring.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
public class MoveRequest {
    @Min(0) @Max(2)
    private int x;
    @Min(0) @Max(2)
    private int y;
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
