package com.egyptFlightReservation.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Airline {
    //class attributes
    private String name;
    private String ID;
    private String location;
    private TreeMap<String, Flight> Flights;

    /// flight number  flight
    //Constructor
    public Airline(String name, String ID, String location) {
        this.name = name;
        this.ID = ID;
        this.location = location;
        this.Flights = new TreeMap<>();
    }

    // method to get name
    public String get_name() {
        return name;
    }

    // method to get ID
    public String get_ID() {
        return ID;
    }

    // method to get location
    public String get_location() {
        return location;
    }

    public void removeFlight(String ID) {
        if (Flights.containsKey(ID)) {
        }
        Flights.remove(ID);
    }

    public boolean isEmptyFlights() {
        return Flights.isEmpty();
    }

    public void addFlight(Flight flight) {
        Flights.put(flight.getFlight_number(), flight);
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
        for (int i = 0; i < seatsInfo.size(); i++) {
            System.out.println("seatsInfo.get(i): " + Arrays.toString(seatsInfo.get(i)));
        }

        for (int i = 0; i < flightsInfo.size(); i++) {
            String[] content = flightsInfo.get(i)[0].split(" ");
            Flights.put(content[1], new Flight(content, (passengersInfo.size() - 1 < i ? null : passengersInfo.get(i)),
                    (ticketsInfo.size() - 1 < i ? null : ticketsInfo.get(i)),
                    (seatsInfo.size() - 1 < i ? null : seatsInfo.get(i))));

//            if(passengersInfo!=null && i < passengersInfo.size())
//                Flights.get(content[1]).setPassengers(passengersInfo.get(i));
//            if(ticketsInfo!=null && i < ticketsInfo.size())
//                Flights.get(content[1]).setTickets(ticketsInfo.get(i));
//            if(seatsInfo!=null && i < seatsInfo.size()){
//                System.out.println("content[1]: " + content[1] + "seatsInfo.get(i): " + seatsInfo.get(i).length );
//                Flights.get(content[1]).setSeats(seatsInfo.get(i));
//            }
            Database.getDatabase().addInSearchingTable(LocalDate.parse(content[4]), Flights.get(content[1]));
            System.out.println("Flight added to database");
        }

    }

}
