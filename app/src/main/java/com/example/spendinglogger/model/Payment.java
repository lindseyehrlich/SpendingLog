package com.example.spendinglogger.model;

import java.util.Date;

public class Payment {
    // Private attributes
    private String title;
    private double amount;
    private Category category;
    private Date date;
    private String notes;

    // Public constructor
    public Payment(String title, double amount, Category category, Date date, String notes) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.notes = notes;
    }
}
