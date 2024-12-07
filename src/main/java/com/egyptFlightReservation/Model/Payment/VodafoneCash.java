package com.egyptFlightReservation.Model.Payment;

public class VodafoneCash extends PaymentMethod{
    private String phoneNumber;
    public VodafoneCash(String holderName, String phoneNumber, int curCash) {
        super(holderName , curCash);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
