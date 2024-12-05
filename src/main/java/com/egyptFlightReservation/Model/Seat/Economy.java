package com.egyptFlightReservation.Model.Seat;

public class Economy extends Seat {
    double baggageAllowance;
    float SeatSpace;

    public Economy(String seatNumber,  boolean availabilityStatus, int seatPrice, double baggageAllowance, float seatSpace) {
        super(seatNumber, availabilityStatus, seatPrice);
        this.baggageAllowance = baggageAllowance;
        this.SeatSpace = seatSpace;
    }

    @Override
    public int getSeatPrice() {
        return super.SeatPrice;
    }

    @Override
    public void DisplayDetails() {
        super.DisplayDetails();
        System.out.println("Baggage Allowance: "+ baggageAllowance);
        System.out.println("Area Seat: "+SeatSpace);
    }
}