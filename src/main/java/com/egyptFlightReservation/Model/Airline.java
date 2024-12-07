package com.egyptFlightReservation.Model;

import java.util.ArrayList;
public class Airline{
    //class attributes
    private String name;
    private int ID;
    private String location;
    private ArrayList<Flight> Fights;
    //Constructor
    public Airline(String name,int ID,String location){
        this.name=name;
        this.ID=ID;
        this.location=location;
    }
    // method to get name
    public String get_name(){
        return name;
    }
    // method to get ID
    public int get_ID(){
        return ID;
    }
    // method to get location
    public String get_location(){
        return location;
    }
}
