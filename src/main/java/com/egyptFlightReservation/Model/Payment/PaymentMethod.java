package com.egyptFlightReservation.Model.Payment;

public abstract class PaymentMethod implements PaymentProcessor {
    protected int curCash;
    public static final int DEFAULT_CASH = 50000;

    public PaymentMethod(int curCash) {
        this.curCash = curCash;
    }

    public int getCurCash() {
        return curCash;
    }

    public abstract void showPaymentDetails();

    public abstract String PublicPaymentDetails();

    @Override
    public abstract String toString();
}
