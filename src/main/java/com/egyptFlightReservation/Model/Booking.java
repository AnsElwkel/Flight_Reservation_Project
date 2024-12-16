package com.egyptFlightReservation.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private String bookingId;
    private String bookingStatus;
    private String bookingDate;
    private String airlineName, airlineID;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String arrivalDate;
    private String countOfSeats;
    /// May be book more than seat !
    private String totalPrice;

    private ArrayList<String> seatNumbers;
    /// May be book more than seat !
    private String clientName;
//    private int frequentFlyerPoints;
//
//    public Booking(String bookingId,String bookingDate , String clientName, String airlineName, String airlineID ,  String flightNumber,
//                   String departureAirport, String arrivalAirport,  String departureDate,
//                   String arrivalDate, int totalPrice, boolean bookingStatus) {
//        this.bookingId = bookingId;
//        this.bookingDate = LocalDate.parse(bookingDate);
//        this.clientName = clientName;
//        this.airlineName = airlineName;
//        this.airlineID = airlineID;
//        this.flightNumber = flightNumber;
//        this.departureAirport = departureAirport;
//        this.arrivalAirport = arrivalAirport;
//        this.departureDate = departureDate;
//        this.arrivalDate = arrivalDate;
//        this.totalPrice = totalPrice;
//        this.bookingStatus = bookingStatus;
//    }

    public Booking(ArrayList<String> info) {
        this.bookingId = info.get(0);
        this.bookingDate = info.get(1);
        this.clientName = info.get(2);
        this.airlineName = info.get(3);
        this.flightNumber = info.get(4);
        this.departureAirport = info.get(5);
        this.arrivalAirport = info.get(6);
        this.departureDate = info.get(7);
        this.arrivalDate = info.get(8);
        this.countOfSeats = info.get(9);
        this.totalPrice = info.get(10);
        this.bookingStatus = info.get(11);
    }

    @Override
    public String toString() {
        return bookingId + " " + bookingDate + " " + clientName + " " +
                airlineName + " " + flightNumber + " " + departureAirport + " " +
                arrivalAirport + " " + departureDate + " " + arrivalDate + " " +
                countOfSeats + " " + totalPrice + " " + bookingStatus;
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Departure Airport: " + departureAirport);
        System.out.println("Departure Time: " + departureDate);
        System.out.println("Arrival Time: " + arrivalDate);
        System.out.println("Client Name: " + clientName);
    }

    public String getClientName() {
        return clientName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String isBookingStatus() {
        return bookingStatus;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getCountOfSeats() {
        return countOfSeats;
    }

    public ArrayList<String> getSeatNumbers() {
        return seatNumbers;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}
