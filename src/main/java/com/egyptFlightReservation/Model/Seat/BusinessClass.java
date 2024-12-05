package com.egyptFlightReservation.Model.Seat;

public class BusinessClass extends Seat {
    boolean SleepSeat;
    boolean WorkFeatures;

    public BusinessClass(String seatNumber, boolean availabilityStatus, int seatPrice, boolean sleepSeat, boolean workFeatures) {
        super(seatNumber, availabilityStatus, seatPrice);
        this.SleepSeat = sleepSeat;
        this.WorkFeatures = workFeatures;
    }

    @Override
    public int getSeatPrice() {
        if(SleepSeat)
            SeatPrice += super.SeatPrice+500;
        if(WorkFeatures)
            SeatPrice += super.SeatPrice+300;
        return SeatPrice;
    }

    @Override
    public void DisplayDetails() {
        super.DisplayDetails();
        System.out.println("Sleep Seat: "+ SleepSeat);
        System.out.println("Work Seat: "+WorkFeatures);
    }
}