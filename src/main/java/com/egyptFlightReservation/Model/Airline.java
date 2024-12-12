package com.egyptFlightReservation.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class Airline{
    //class attributes
    private String name;
    private String ID;
    private String location;
    private TreeMap<String , Flight> Flights;
    //Constructor
    public Airline(String name,String ID,String location){
        this.name=name;
        this.ID=ID;
        this.location=location;
    }
    // method to get name
    public String get_name(){
        return name;
    }
    // method to get ID
    public String get_ID(){
        return ID;
    }
    // method to get location
    public String get_location(){
        return location;
    }
    public void removeFlight(String ID){
        if(Flights.containsKey(ID)){}
            Flights.remove(ID);
    }
    public void addFlight(Flight flight){
        Flights.put(flight.getFlight_number() , flight);
    }
    public void setSchedule(String ID , LocalDate departureDate , LocalDate arrivalDate){
        Flights.get(ID).setArrivalDate(arrivalDate);
        Flights.get(ID).setDepartureDate(departureDate);
    }
    public boolean expandCountOfSeats(String flightNumber,int countOfRows , int countOfFirstClass,int countOfBusinessClass ,int countOfEconomyClass){
        if(!Flights.get(flightNumber).expandCountOfSeats(countOfRows , countOfFirstClass , countOfBusinessClass , countOfEconomyClass)){
            System.out.println("No flight found for "+flightNumber + "in expand count of seats in airline funciton");
            return false;
        }
        return true;
    }
}
