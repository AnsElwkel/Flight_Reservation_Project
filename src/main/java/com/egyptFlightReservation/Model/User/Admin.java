package com.egyptFlightReservation.Model.User;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Flight;

public class Admin extends User{
    private String AirlineName;
    public Admin(String username, String name , String password ,String email , String AirlineName) {
        super(username,name,email,password);
        this.AirlineName = AirlineName;
    }
    public void addFlight(String AirlineNumber ,Flight newFlight){
        Database.getDatabase().addNewFlight(AirlineNumber , newFlight);
    }
    public void removeFlight(String flightNumber){
        Database.getDatabase().removeFlight(flightNumber);

    }
    public void updateSchedule(String flightNumber ,String newArrivalTime, String newDepartureTime) {}

    //public void updateSeatAvailability(String flightNumber ,String seatNumber,boolean newStatus){}

    public void updateCountOfSeat(String flightNumber,int countOfFirstClass,int countOfEconomyClass ,int countOfBusinessClass){}
}
