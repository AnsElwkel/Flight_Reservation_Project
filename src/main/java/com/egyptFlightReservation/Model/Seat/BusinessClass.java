package com.egyptFlightReservation.Model.Seat;

public class BusinessClass extends Seat {
    public BusinessClass(String seatNumber, boolean availabilityStatus, int seatPrice , String seatFeatures) {
        super(seatNumber, availabilityStatus, seatPrice , seatFeatures);
    }


    @Override
    public void showSeatFeatures() {
        System.out.println("Some advantage of Business seat: " + seatFeatures);
    }
}