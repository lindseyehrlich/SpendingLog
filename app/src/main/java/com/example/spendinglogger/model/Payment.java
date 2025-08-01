package com.example.spendinglogger.model;

import java.util.Date;

public class Payment {
    private String title;
    private double amount;
    private Category category;
    private Date date;
    private String notes;

    public Payment(double amount, String title, Category category, Date date, String notes) {
        this.amount = amount;
        this.title = title;
        this.category = category;
        this.date = date;
        this.notes = notes;
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
