package com.egyptFlightReservation.Model.Payment;

public class VodafoneCash extends PaymentMethod {
    private String phoneNumber;
    public VodafoneCash(String phoneNumber , int cash) {
        super(cash);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void showPaymentDetails() {
        System.out.println("Phone Number : " + this.phoneNumber);
        System.out.println("Cash : " + getCurCash());
    }

    @Override
    public String PublicPaymentDetails() {
        return new String("Vodafone Cash : " + this.phoneNumber);
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
