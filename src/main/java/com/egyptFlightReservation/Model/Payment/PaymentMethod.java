package com.egyptFlightReservation.Model.Payment;

public abstract class PaymentMethod implements PaymentProcessor {
    private String holderName;
    private int curCash;

    public PaymentMethod(String holderName , int curCash) {
        this.holderName = holderName;
        this.curCash = curCash;
    }
    public String getHolderName(){
        return holderName;
    }
    public int getCurCash(){
        return curCash;
    }

    @Override
    public void paymentProcess() {

    }
}
