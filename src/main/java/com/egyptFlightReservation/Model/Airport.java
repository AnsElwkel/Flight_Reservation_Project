package com.egyptFlightReservation.Model;

public class Airport {
    private String name;
    private String airportCode;
    private String location;

    public Airport(String name, String airportCode, String location) {
        this.name = name;
        this.airportCode = airportCode;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getAirportcode() {
        return airportCode;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + " " + airportCode + " " + location;
    }
}
