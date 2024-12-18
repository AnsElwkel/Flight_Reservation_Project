package com.egyptFlightReservation.Model.Payment;

public class PayPal extends PaymentMethod {
    private String paypalEmail;

    public PayPal(String paypalEmail, double cash) {
        super(cash);
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    @Override
    public void showPaymentDetails() {
        System.out.println("Paypal Email : " + this.paypalEmail);
        System.out.println("Cash : " + getCurCash());
    }

    @Override
    public String PublicPaymentDetails() {
        return new String("Paypal : " + this.paypalEmail);
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
        return paypalEmail + " " + getCurCash();
    }
}
