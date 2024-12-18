package com.egyptFlightReservation.Model.Payment;

import java.util.ArrayList;

public class DebitCard extends PaymentMethod {
    private String holderName, cardNumber, expiryDate, cvv;

    public DebitCard(String holderName, String cardNumber, String expiryDate, String cvv, double cash) {
        super(cash);
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public DebitCard(ArrayList<String> info) {
        super(PaymentMethod.DEFAULT_CASH);
        this.holderName = info.get(0);
        this.cardNumber = info.get(1);
        this.expiryDate = info.get(2);
        this.cvv = info.get(3);
    }

    public String getHolderName() {
        return holderName;
    }


    @Override
    public void showPaymentDetails() {
        System.out.println("Card Number : " + this.cardNumber);
        System.out.println("Expiry Date : " + this.expiryDate);
        System.out.println("CVV : " + this.cvv);
        System.out.println("Cash : " + getCurCash());
    }

    @Override
    public String PublicPaymentDetails() {
        return new String("Debit Card : " + this.cardNumber);
    }

    @Override
    public boolean paymentProcess(double amount) {
        if (getCurCash() >= amount) {
            curCash -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return holderName + " " + cardNumber + " " + expiryDate + " " + cvv + " " + getCurCash();
    }
}
