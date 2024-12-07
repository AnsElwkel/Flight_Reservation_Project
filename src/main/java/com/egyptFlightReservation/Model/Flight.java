package com.egyptFlightReservation.Model;

import com.egyptFlightReservation.Model.Seat.*;
import com.egyptFlightReservation.Model.*;

import java.util.ArrayList;

public class Flight {
    private ArrayList<Passenger> passengers;
    private ArrayList<ArrayList<Seat>> Seats;
    private String flight_number;
    private String airline_name;
    private String departure_airport;
    private String arrival_airport;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int standardPrice;

    public Flight(String flight_number, String departure_airport, String arrival_airport, String departureTime, String arrivalTime, int totalSeats, int standardPrice) {
        this.flight_number = flight_number;
        this.departure_airport = departure_airport;
        this.arrival_airport = departure_airport;
        this.departureTime = departureTime;
        this.arrivalTime = departureTime;
        this.totalSeats = totalSeats;
        this.standardPrice = standardPrice;


    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getStandardPrice() {
        return standardPrice;
    }


}