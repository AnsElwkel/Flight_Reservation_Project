package com.egyptFlightReservation.Model.User;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Flight;

public class Admin extends User{
    public static final String MAIN_ADMIN_NAME = "ANASELWKEL" ,  MAIN_ADMIN_PASSWORD = "Anas_11";
    private String AirlineName;
    public Admin(String username, String name , String password ,String email , String AirlineName) {
        super(username,name,email,password);
        this.AirlineName = AirlineName;
    }
    public void addFlight(Flight newFlight){
        Database.getDatabase().addNewFlight(newFlight);
    }
    public void removeFlight(String flightNumber){
        Database.getDatabase().removeFlight(flightNumber);
    }
    public void updateSchedule(String flightNumber ,String departureDate, String arrivalDate) {
        Database.getDatabase().updateSchedule(flightNumber , departureDate , arrivalDate);
    }

    //public void updateSeatAvailability(String flightNumber ,String seatNumber,boolean newStatus){}

    public boolean expandCountOfSeats(String flightNumber, int newCountOfRows,int countOfFirstClass,int countOfBusinessClass ,int countOfEconomyClass){
        return Database.getDatabase().expandCountOfSeats(flightNumber , newCountOfRows, countOfFirstClass , countOfBusinessClass , countOfEconomyClass);
    }
}
