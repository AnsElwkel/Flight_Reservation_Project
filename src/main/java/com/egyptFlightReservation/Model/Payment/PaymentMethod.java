package com.egyptFlightReservation.Model.Payment;

public abstract class PaymentMethod implements PaymentProcessor {
    protected double curCash;
    public static final int DEFAULT_CASH = 50000;

    public PaymentMethod(double curCash) {
        this.curCash = curCash;
    }

    public double getCurCash() {
        return curCash;
    }

    public abstract void showPaymentDetails();

    public abstract String PublicPaymentDetails();

    @Override
    public abstract String toString();
}
