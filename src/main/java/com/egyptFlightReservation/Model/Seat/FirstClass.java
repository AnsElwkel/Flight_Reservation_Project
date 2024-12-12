package com.egyptFlightReservation.Model.Seat;

public class FirstClass extends Seat {

    public FirstClass(String seatNumber, boolean availabilityStatus, int seatPrice, String seatFeatures) {
        super(seatNumber, availabilityStatus, seatPrice , seatFeatures);
    }

    @Override
    public void showSeatFeatures() {
        System.out.println("Some advantage of First Class Seat: " + seatFeatures);
    }
}