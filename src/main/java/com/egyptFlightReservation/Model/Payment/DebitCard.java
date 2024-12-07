package com.egyptFlightReservation.Model.Payment;

public class DebitCard extends PaymentMethod {
    private String cardNumber , expiryDate , cvv;

    public DebitCard(String holderName, String cardNumber, String expiryDate, String cvv, int curCash){
        super(holderName , curCash);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    DebitCard(String[] info){
        super(info[0] ,Integer.parseInt(info[4]));
        this.cardNumber = info[1];
        this.expiryDate = info[2];
        this.cvv = info[3];
    }
}
