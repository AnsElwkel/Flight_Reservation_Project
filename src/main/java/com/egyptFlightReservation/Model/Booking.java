package com.egyptFlightReservation.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private String bookingId;
    private boolean bookingStatus;
    private LocalDate bookingDate;
    private String airlineName;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String arrivalDate;
    private int countOfSeats;    ///May be book more than seat !
    private int totalPrice;

    private ArrayList<String> seatNumbers;  ///May be book more than seat !
    private String clientName;
//    private int frequentFlyerPoints;

    public Booking(String bookingId, String clientName, String flightNumber, String seatNumber,
                   String departureAirport, String arrivalAirport, String airlineName, String departureDate,
                   String arrivalDate, int totalPrice, boolean bookingStatus) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.seatNumber = seatNumber;
        this.clientName = clientName;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
        this.arrivalAirport = arrivalAirport;
        this.airlineName = airlineName;
        this.bookingDate = LocalDate.now();
    }

    public Booking(String[] info) {
        bookingId=info[0];
        clientName=info[1];
        flightNumber=info[2];
        seatNumber=info[3];
        departureAirport=info[4];
        arrivalAirport=info[5];
        airlineName=info[6];
        departureDate=info[7];
        arrivalDate=info[8];
        totalPrice=Integer.parseInt(info[9]);
        bookingStatus=Boolean.parseBoolean(info[10]);
        this.bookingDate = LocalDate.now();
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Departure Airport: " + departureAirport);
        System.out.println("Departure Time: " + departureDate);
        System.out.println("Arrival Time: " + arrivalDate);
        System.out.println("Seat Number: " + seatNumber);
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

    public String getSeatNumber() {
        return seatNumber;
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

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public ArrayList<String> getSeatNumbers() {
        return seatNumbers;
    }
    public boolean getBookingStatus() {
        return bookingStatus;
    }
}
