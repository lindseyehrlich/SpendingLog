package com.example.spendinglogger.model;

import java.util.Date;

public class Payment {
    // Private attributes
    private String title;
    private Double amount;
    private Category category;
    private Date date;
    private String notes;

    // Public constructor
    public Payment(String title, Double amount, Category category, Date date, String notes) {
        setTitle(title);
        setAmount(amount);
        setCategory(category);
        setDate(date);
        setNotes(notes);
    }

    private void checkString(String string, String item) {
        if (string == null) {
            String exceptionString = String.format("%s cannot be null", item);
            throw new NullPointerException(exceptionString);
        }

        if (string.isBlank()) {
            String exceptionString = String.format("%s cannot be empty or whitespace", item);
            throw new IllegalArgumentException(exceptionString);
        }
    }

    private void checkAmount(Double amount) {
        if (amount == null) {
            throw new NullPointerException("Amount cannot be null.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
    }

    private void checkCategory(Category category) {
        if (category == null) {
            throw new NullPointerException("Category cannot be null.");
        }
    }

    private void checkDate(Date date) {
        if (date == null) {
            throw new NullPointerException("Date cannot be null.");
        }
    }

    // Getters
    public String getTitle() { return title; }
    public Double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public Date getDate() { return date; }
    public String getNotes() { return notes; }

    // Setters
    public void setTitle(String title) {
        checkString(title, "Title");
        this.title = title;
    }

    public void setAmount(Double amount) {
        checkAmount(amount);
        this.amount = amount;
    }

    public void setCategory(Category category) {
        checkCategory(category);
        this.category = category;
    }

    public void setDate(Date date) {
        checkDate(date);
        this.date = date;
    }

    public void setNotes(String notes) {
        checkString(notes, "Notes");
        this.notes = notes;
    }
}
