package com.egyptFlightReservation.Model.Seat;

public class FirstClass extends Seat {
    boolean SleepSeat;
    boolean privacyFeatures;

    public FirstClass(String seatNumber, boolean availabilityStatus, int seatPrice, boolean sleepSeat, boolean privacyFeatures) {
        super(seatNumber, availabilityStatus, seatPrice);
        this.SleepSeat = sleepSeat;
        this.privacyFeatures = privacyFeatures;
    }

    @Override
    public int getSeatPrice() {
        if(SleepSeat)
            SeatPrice += super.SeatPrice+500;
        if(privacyFeatures)
            SeatPrice += super.SeatPrice+1000;
        return SeatPrice;
    }

    @Override
    public void DisplayDetails(){
        super.DisplayDetails();
        System.out.println("Sleep Seat: "+ SleepSeat);
        System.out.println("Private Seat: "+privacyFeatures);
    }

}