package com.egyptFlightReservation.Model;

public class Airport {
    private String name;
    private String airportcode;
    private String location;
    public Airport(String name,String airportcode,String location)
    {
        this.name=name;
        this.airportcode=airportcode;
        this.location=location;
    }
    public String getName()
    {
        return name;
    }

    public String getAirportcode() {
        return airportcode;
    }
    public String getLocation()
    {
        return location;
    }
}
