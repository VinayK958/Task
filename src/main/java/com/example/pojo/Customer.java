package com.example.pojo;

import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private List<Transaction> transactions;

    // Constructors
    public Customer(Long id, String name, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}