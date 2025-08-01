package com.example.spendinglogger.model;

import java.util.ArrayList;

public class PaymentManager {
    private static PaymentManager instance;
    private ArrayList<Payment> paymentList = new ArrayList<>();

    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }

        return instance;
    }

    public void addPayment(Payment payment) { paymentList.add(payment); }

    // getters
    public ArrayList<Payment> getPaymentList() { return paymentList; }
}
