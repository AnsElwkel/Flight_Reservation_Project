package com.egyptFlightReservation.Model.Payment;

public abstract class PaymentMethod implements PaymentProcessor {
    protected int curCash;
    public PaymentMethod(int curCash) {
        this.curCash = curCash;
    }
    public int getCurCash(){
        return curCash;
    }
    public abstract void showPaymentDetails();
    public abstract String PublicPaymentDetails();
}
