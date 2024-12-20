package com.egyptFlightReservation.Model;

import Tools.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.HashMap;

public class Airline {
    private String name;
    private String ID;
    private String location;
    private TreeMap<String, Flight> Flights;
    private HashMap<String , String> clientsWhoRated;
    private int ratingCount , sumOfRatings;
    private double ratingAverage;
    public Airline(String name, String ID, String location , double ratingAverage , int sumOfRatings , int ratingCount) {
        this.name = name;
        this.ID = ID;
        this.location = location;
        this.Flights = new TreeMap<>();
        this.clientsWhoRated = new HashMap<>();
        this.ratingCount = 0;
        this.sumOfRatings = 0;
        this.ratingAverage = 0.0;
    }

    public ArrayList<String> getAllRatings(){
        ArrayList<String> ratings = new ArrayList<>();
        for(String key : this.clientsWhoRated.keySet()){
            ratings.add(key+"~"+clientsWhoRated.get(key));
        }
        return ratings;
    }
    public void setRate(String username , int rate , String review){
        clientsWhoRated.put(username , String.valueOf(rate) + "~" + review);
        ratingCount++;
        sumOfRatings += rate;
        ratingAverage = ((double)sumOfRatings)/ratingCount;
    }

    public boolean isRatedBefore(String username){
        if(this.clientsWhoRated.containsKey(username)){
            return true;
        }
        return false;
    }

    public String get_name() {
        return name;
    }

    public String get_ID() {
        return ID;
    }

    public String get_location() {
        return location;
    }

    public void removeFlight(String ID) {
        if (Flights.containsKey(ID)) {
            Flights.remove(ID);
        }
    }

    public boolean isEmptyFlights() {
        return Flights.isEmpty();
    }

    public void addFlight(Flight flight) {
        Flights.put(flight.getFlightNumber(), flight);
    }

    public void setSchedule(String ID, LocalDate departureDate, LocalDate arrivalDate) {
        Flights.get(ID).setArrivalDate(arrivalDate);
        Flights.get(ID).setDepartureDate(departureDate);
    }

    public boolean expandCountOfSeats(String flightNumber, int countOfRows, int countOfFirstClass, int countOfBusinessClass, int countOfEconomyClass) {
        if (!Flights.get(flightNumber).expandCountOfSeats(countOfRows, countOfFirstClass, countOfBusinessClass, countOfEconomyClass)) {
            System.out.println("No flight found for " + flightNumber + "in expand count of seats in airline funciton");
            return false;
        }
        Menu.showMessage("Seats successfully updated" , 1);
        return true;
    }

    public ArrayList<String> getflightNumbers() {
        return Flights.keySet().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public ArrayList<String> getPassenegersInfoOfFlight(String flightNumber) {
        return Flights.get(flightNumber).getPassengersInfo();
    }

    public boolean isEmptyPassengers(String flightNumber) {
        return Flights.get(flightNumber).isEmptyPassengers();
    }

    public boolean isEmptyTickets(String flightNumber) {
        return Flights.get(flightNumber).isEmptyTickets();
    }

    public ArrayList<String> getTicketsInfoOfFlight(String flightNumber) {
        return Flights.get(flightNumber).getTicketsInfo();
    }

    public ArrayList<String> getFlightInfo(String flightNumber) {
        ArrayList<String> info = new ArrayList<>();
        info.add(Flights.get(flightNumber).toString());
        return info;
    }

    public ArrayList<String> getSeatsInfo(String flightNumber) {
        return Flights.get(flightNumber).getSeatsInfo();
    }

    public void setFlights(ArrayList<String[]> flightsInfo,
                           ArrayList<String[]> passengersInfo,
                           ArrayList<String[]> ticketsInfo,
                           ArrayList<String[]> seatsInfo) {

        for (int i = 0; i < flightsInfo.size(); i++) {
            String[] content = flightsInfo.get(i)[0].split(" ");
            Flights.put(content[1], new Flight(content, (passengersInfo.size() - 1 < i ? null : passengersInfo.get(i)),
                    (ticketsInfo.size() - 1 < i ? null : ticketsInfo.get(i)),
                    (seatsInfo.size() - 1 < i ? null : seatsInfo.get(i))));

            Database.getDatabase().addInSearchingTable(LocalDate.parse(content[4]), Flights.get(content[1]));
//            System.out.println("Flight added to database");
        }

    }

    public ArrayList<Flight> getFlights(){
        return new ArrayList<Flight>(Flights.values());
    }
    @Override
    public String toString() {
        return name + " " + ID + " " + ratingCount + " " + ratingAverage;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public int getSumOfRatings() {
        return sumOfRatings;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
