package com.egyptFlightReservation.Model.Payment;

public class PayPal extends PaymentMethod {
    private String paypalEmail;

    public PayPal(String holderName , String paypalEmail ,int curCash){
        super(holderName , curCash);
        this.paypalEmail = paypalEmail;
    }
    public PayPal(String[] info){
        super(info[0] , Integer.parseInt(info[2]));
        this.paypalEmail = info[1];
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }
}
