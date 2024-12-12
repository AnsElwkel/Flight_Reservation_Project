package com.egyptFlightReservation.Model.Payment;

import java.util.ArrayList;

public class MasterCard extends PaymentMethod {
    private String holderName , cardNumber , expiryDate, cvv;

    public MasterCard(String holderName , String cardNumber, String expiryDate, String cvv , int cash) {
        super(cash);
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    public MasterCard(ArrayList<String> info){
        super(20000);
        this.holderName = info.get(0);
        this.cardNumber = info.get(1);
        this.expiryDate = info.get(2);
        this.cvv = info.get(3);
    }
    public String getHolderName(){
        return holderName;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
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
        return new String("Master Card : " + this.cardNumber );
    }
    @Override
    public boolean paymentProcess(int amount) {
        if(getCurCash() >= amount){
            curCash -= amount;
            return true;
        }
        return false;
    }
}
