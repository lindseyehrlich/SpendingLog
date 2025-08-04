package com.example.spendinglogger.model;

import java.util.ArrayList;

public class PaymentManager {
    private static volatile PaymentManager instance;
    private ArrayList<Payment> paymentList = new ArrayList<>();

    public static PaymentManager getInstance() {
        if (instance == null) {
            synchronized (PaymentManager.class) {
                if (instance == null) {
                    instance = new PaymentManager();
                }
            }
        }

        return instance;
    }

    public void addPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Cannot add null payment.");
        }

        paymentList.add(payment);
    }

    public void removePayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Cannot remove null payment.");
        }

        // Remove later?
        if (!paymentList.contains(payment)) {
            throw new IllegalArgumentException("Payment does not exist.");
        }

        paymentList.remove(payment);
    }

    // Getters
    public ArrayList<Payment> getPaymentList() { return paymentList; }

    // Setters
    public void setPaymentList(ArrayList<Payment> paymentList) { this.paymentList = paymentList; }
}
