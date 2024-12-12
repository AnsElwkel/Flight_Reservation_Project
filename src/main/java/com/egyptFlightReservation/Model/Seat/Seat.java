package com.egyptFlightReservation.Model.Seat;

public abstract class Seat {
    protected String SeatNumber;
    protected boolean AvailabilityStatus;
    protected int SeatPrice;
    protected String seatFeatures; /// admin will input this att


    public Seat(String seatNumber, boolean availabilityStatus, int seatPrice , String seatFeatures) {
        this.SeatNumber = seatNumber;
        this.AvailabilityStatus = availabilityStatus;
        this.SeatPrice = seatPrice;
        this.seatFeatures = seatFeatures;
    }

    public int getSeatPrice() {
        return SeatPrice;
    }

    public boolean isAvailabilityStatus() {
        return AvailabilityStatus;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    public abstract void showSeatFeatures();
    public void setSeatFeatures(String seatFeatures) {
        this.seatFeatures = seatFeatures;
    }

    public void setSeatNumber(String seatNumber) {
        SeatNumber = seatNumber;
    }

    public void DisplayDetails(){
        System.out.println("Seat Number: "+SeatNumber);
        System.out.println("Seat Price: "+SeatPrice);
        System.out.println("Availability of Seat: "+ AvailabilityStatus);
    }
}