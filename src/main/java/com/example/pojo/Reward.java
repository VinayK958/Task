package com.example.pojo;

public class Reward {
    private Long customerId;
    private int month;
    private int points;

    // Constructors
    public Reward(Long customerId, int month, int points) {
        this.customerId = customerId;
        this.month = month;
        this.points = points;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
// Getters and setters
}