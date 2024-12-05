package com.egyptFlightReservation.Model.Seat;

public abstract class Seat {
    protected String SeatNumber;
    protected boolean AvailabilityStatus;
    protected int SeatPrice;

    public Seat(String seatNumber, boolean availabilityStatus, int seatPrice) {
        this.SeatNumber = seatNumber;
        this.AvailabilityStatus = availabilityStatus;
        this.SeatPrice = seatPrice;
    }

    public abstract int getSeatPrice() ;

    public boolean isAvailabilityStatus() {
        return AvailabilityStatus;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    public void DisplayDetails(){
        System.out.println("Seat Number: "+SeatNumber);
        System.out.println("Seat Price: "+SeatPrice);
        System.out.println("Availability of Seat: "+ AvailabilityStatus);
    }
}