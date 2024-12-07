package com.egyptFlightReservation.Model.Payment;

public class MasterCard extends PaymentMethod{
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public MasterCard(String holderName , String cardNumber, String expiryDate, String cvv , int curCash) {
        super(holderName , curCash);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    MasterCard(String[] info){
        super(info[0] ,Integer.parseInt(info[4]));
        this.cardNumber = info[1];
        this.expiryDate = info[2];
        this.cvv = info[3];
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
}
