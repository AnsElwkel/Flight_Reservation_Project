package com.egyptFlightReservation.Model;

import Tools.DateValidation;
import com.egyptFlightReservation.Model.Seat.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;

import static Tools.DateValidation.dateFormatter;

public class Flight {
    private static final int MAX_COLS = 26, MAX_ROWS = 10,
            MIN_COLS = 10, MIN_ROWS = 4;
    private ArrayList<Passenger> passengers;
    private ArrayList<ArrayList<Seat>> Seats;
    private ArrayList<Ticket> reservedTickets;
    private String flight_number;
    private String departure_airport;
    private String arrival_airport;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private int totalSeats, cntTotalSeatRows, cntTotalSeatCols,
            cntFirstClassCols, cntBusinessClassCols, cntEconomyClassCols,
            cntOfAvailableFirstClassSeats , cntOfAvailableBusinessClassSeats, cntOfAvailableEconomyClassSeats, // should be decremented when booking seat
            firstClassPrice, businessClassPrice, economyClassPrice;
    private String airlineName , firstClassFeatures , businessClassFeatures , economyClassFeatures;

    public Flight(ArrayList<String> info) {
        this.flight_number = info.get(0);
        this.departure_airport = info.get(1);
        this.arrival_airport = info.get(2);
        this.departureDate = LocalDate.parse(info.get(3) , dateFormatter);
        this.arrivalDate = LocalDate.parse(info.get(4) , dateFormatter);
//        this.cntTotalSeatRows = Integer.parseInt(info.get(5));
//        this.cntFirstClassCols = Integer.parseInt(info.get(7));
//        this.cntBusinessClassCols = Integer.parseInt(info.get(9));
//        this.cntEconomyClassCols = Integer.parseInt(info.get(11));
        this.passengers = new ArrayList<>();
        this.firstClassFeatures = info.get(13);
        this.businessClassFeatures = info.get(14);
        this.economyClassFeatures = info.get(15);
        this.airlineName = info.get(16);

        expandCountOfSeats(Integer.parseInt(info.get(5)), Integer.parseInt(info.get(7)),
                Integer.parseInt(info.get(9)), Integer.parseInt(info.get(11)));

        this.cntTotalSeatCols = Integer.parseInt(info.get(6));
        this.totalSeats = cntTotalSeatRows * cntTotalSeatCols;   // calculate total seats
        this.firstClassPrice = Integer.parseInt(info.get(8));
        this.businessClassPrice = Integer.parseInt(info.get(10));
        this.economyClassPrice = Integer.parseInt(info.get(12));
//        this.Seats = new ArrayList<ArrayList<Seat>>(cntTotalSeatRows); // test this
//        this.Seats.addAll(new ArrayList<>(cntTotalSeatCols));
//        setSeatNumbers();
//        / add features of seats
//        addSeatFeatures(firstClassFeatures , businessClassFeatures , economyClassFeatures);

        this.cntOfAvailableFirstClassSeats = getCntOfCertainSeats(this.cntFirstClassCols);
        this.cntOfAvailableBusinessClassSeats = getCntOfCertainSeats(this.cntBusinessClassCols);
        this.cntOfAvailableEconomyClassSeats = getCntOfCertainSeats(this.cntEconomyClassCols);
    }
//
//    public void addSeatFeatures(String firstClassFeatures , String businessClassFeatures , String economyClassFeatures){
//        for(int i = 0 ;i < cntOfAvailableFirstClassSeats;i++)
//            for(Seat seat : Seats.get(i))
//                seat.setSeatFeatures(firstClassFeatures);
//
//
//        for(int i = cntOfAvailableFirstClassSeats ;i < cntOfAvailableFirstClassSeats+cntOfAvailableBusinessClassSeats;i++)
//            for(Seat seat : Seats.get(i))
//                seat.setSeatFeatures(businessClassFeatures);
//
//
//        for(int i = cntOfAvailableFirstClassSeats + cntOfAvailableBusinessClassSeats ;
//            i < cntOfAvailableFirstClassSeats+cntOfAvailableBusinessClassSeats+cntOfAvailableEconomyClassSeats;i++)
//            for(Seat seat : Seats.get(i))
//                seat.setSeatFeatures(economyClassFeatures);
//    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
    public void addTicket(Ticket ticket){
        reservedTickets.add(ticket);
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getCntOfCertainSeats(int col){
        return col * getCntTotalSeatRows();
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getAirlineName() { return airlineName;}

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public ArrayList<ArrayList<Seat>> getSeats() {
        return Seats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getCntTotalSeatRows() {
        return cntTotalSeatRows;
    }

    public int getCntTotalSeatCols() {
        return cntTotalSeatCols;
    }

    public int getCntFirstClassCols() {
        return cntFirstClassCols;
    }

    public int getCntBusinessClassCols() {
        return cntBusinessClassCols;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public int getCntEconomyClassCols() {
        return cntEconomyClassCols;
    }

    public int getBusinessClassPrice() {
        return businessClassPrice;
    }

    public int getEconomyClassPrice() {
        return economyClassPrice;
    }

    public void setCntFirstClassCols(int cntFirstClassCols) {
        this.cntFirstClassCols = cntFirstClassCols;
    }

    public void setCntBusinessClassCols(int cntBusinessClassCols) {
        this.cntBusinessClassCols = cntBusinessClassCols;
    }

    public void setCntEconomyClassCols(int cntEconomyClassCols) {
        this.cntEconomyClassCols = cntEconomyClassCols;
    }

    public boolean expandCountOfSeats(int newCountOfRows, int newCountOfFirstClass, int newCountOfBusinessClass, int newCountOfEconomyClass) {
        if (!(this.cntFirstClassCols <= newCountOfFirstClass
                && this.cntBusinessClassCols <= newCountOfBusinessClass
                && this.cntEconomyClassCols <= newCountOfEconomyClass
                && this.cntTotalSeatRows <= newCountOfRows && newCountOfRows <= MAX_ROWS
                && newCountOfFirstClass + newCountOfBusinessClass + newCountOfEconomyClass <= MAX_COLS)) {
            return false;
        }
        //add new rows
        while (this.cntTotalSeatRows < newCountOfRows) {
            Seats.add(new ArrayList<Seat>());
            ++this.cntTotalSeatRows;
        }
        while (this.cntFirstClassCols < newCountOfFirstClass) {
            for (ArrayList<Seat> row : Seats)
                row.add(cntFirstClassCols, new FirstClass("", true, firstClassPrice , firstClassFeatures));
            ++this.cntFirstClassCols;
            ++this.cntTotalSeatCols;
        }
        while (this.cntBusinessClassCols < newCountOfBusinessClass) {
            for (ArrayList<Seat> row : Seats)
                row.add(cntFirstClassCols +cntBusinessClassCols, new BusinessClass("", true, businessClassPrice,businessClassFeatures));
            ++this.cntBusinessClassCols;
            ++this.cntTotalSeatCols;
        }
        while (this.cntEconomyClassCols < newCountOfEconomyClass) {
            for (ArrayList<Seat> row : Seats)
                row.add(cntFirstClassCols + cntBusinessClassCols+cntEconomyClassCols, new EconomyClass("", true, economyClassPrice, economyClassFeatures));
            ++this.cntEconomyClassCols;
            ++this.cntTotalSeatCols;
        }

        setSeatNumbers();
        return true;
    }

    public void setSeatNumbers() {
        for (char rowCharcter = 'A'; rowCharcter < this.cntTotalSeatRows + 'A'; rowCharcter++) {
            for (int i = 0; i < this.cntTotalSeatCols; i++)
                Seats.get(rowCharcter - 'A').get(i).setSeatNumber(String.valueOf(rowCharcter) + String.valueOf(i + 1));
        }
        System.out.println("Seat Number is updated");
    }

    public int getCntOfAvailableFirstClassSeats() {
        return cntOfAvailableFirstClassSeats;
    }

    public int getCntOfAvailableBusinessClassSeats() {
        return cntOfAvailableBusinessClassSeats;
    }

    public int getCntOfAvailableEconomyClassSeats() {
        return cntOfAvailableEconomyClassSeats;
    }
}