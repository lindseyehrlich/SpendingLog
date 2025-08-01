package com.example.spendinglogger.model;

public class Payment {
    private String title;
    private double amount;
    private Category category;
    private String notes;

    public Payment(double amount, String title, String notes, Category category) {
        this.amount = amount;
        this.title = title;
        this.notes = notes;
        this.category = category;
    }

    // getters
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public String getNotes() { return notes; }

    // setters
    public void setTitle(String title) { this.title = title; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCategory(Category category) { this.category = category; }
    public void setNotes(String notes) { this.notes = notes; }
}
