package com.egyptFlightReservation.Model.Seat;

public class EconomyClass extends Seat {

    public EconomyClass(String seatNumber, boolean availabilityStatus, int seatPrice, String seatFeatures) {
        super(seatNumber, availabilityStatus, seatPrice, seatFeatures);
    }

    @Override
    public void showSeatFeatures() {
        System.out.println("Some advantage of Economy seat: " + seatFeatures);
    }

}