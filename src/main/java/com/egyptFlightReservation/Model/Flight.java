package com.egyptFlightReservation.Model;

import Tools.DateValidation;
import com.egyptFlightReservation.Model.Seat.*;
import com.sun.security.jgss.GSSUtil;

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
            cntOfAvailableFirstClassSeats, cntOfAvailableBusinessClassSeats, cntOfAvailableEconomyClassSeats, // should be decremented when booking seat
            firstClassPrice, businessClassPrice, economyClassPrice , firstClassPremiumPoints,
            businessClassPremiumPoints , economyClassPremiumPoints;
    private String airlineName, firstClassFeatures, businessClassFeatures, economyClassFeatures;

    public Flight(ArrayList<String> info) {
        this.Seats = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.reservedTickets = new ArrayList<>();

        this.flight_number = info.get(0);
        this.departure_airport = info.get(1);
        this.arrival_airport = info.get(2);
        this.departureDate = LocalDate.parse(info.get(3), dateFormatter);
        this.arrivalDate = LocalDate.parse(info.get(4), dateFormatter);
        this.firstClassFeatures = info.get(13);
        this.businessClassFeatures = info.get(14);
        this.economyClassFeatures = info.get(15);
        this.airlineName = info.get(16);
        this.firstClassPremiumPoints = Integer.parseInt(info.get(17));
        this.businessClassPremiumPoints = Integer.parseInt(info.get(18));
        this.economyClassPremiumPoints = Integer.parseInt(info.get(19));


        this.firstClassPrice = Integer.parseInt(info.get(8));
        this.businessClassPrice = Integer.parseInt(info.get(10));
        this.economyClassPrice = Integer.parseInt(info.get(12));
        expandCountOfSeats(Integer.parseInt(info.get(5)), Integer.parseInt(info.get(7)),
                Integer.parseInt(info.get(9)), Integer.parseInt(info.get(11)));

        this.cntTotalSeatCols = Integer.parseInt(info.get(6));
        this.totalSeats = cntTotalSeatRows * cntTotalSeatCols;   // calculate total seats

        this.cntOfAvailableFirstClassSeats = getCntOfCertainSeats(this.cntFirstClassCols);
        this.cntOfAvailableBusinessClassSeats = getCntOfCertainSeats(this.cntBusinessClassCols);
        this.cntOfAvailableEconomyClassSeats = getCntOfCertainSeats(this.cntEconomyClassCols);

    }

    //Constructor to load from files
    public Flight(String[] info, String[] passengerInfo, String[] ticketInfo, String[] seatInfo) {
        this.passengers = new ArrayList<>();
        this.Seats = new ArrayList<>();
        this.reservedTickets = new ArrayList<>();

        this.airlineName = info[0];
        this.flight_number = info[1];
        this.departure_airport = info[2];
        this.arrival_airport = info[3];
        this.departureDate = LocalDate.parse(info[4], dateFormatter);
        this.arrivalDate = LocalDate.parse(info[5], dateFormatter);
        this.totalSeats = Integer.parseInt(info[6]);
        this.cntTotalSeatRows = Integer.parseInt(info[7]);
        this.cntTotalSeatCols = Integer.parseInt(info[8]);
        this.cntFirstClassCols = Integer.parseInt(info[9]);
        this.cntBusinessClassCols = Integer.parseInt(info[10]);
        this.cntEconomyClassCols = Integer.parseInt(info[11]);
        this.cntOfAvailableFirstClassSeats = Integer.parseInt(info[12]);
        this.cntOfAvailableBusinessClassSeats = Integer.parseInt(info[13]);
        this.cntOfAvailableEconomyClassSeats = Integer.parseInt(info[14]);
        this.firstClassPrice = Integer.parseInt(info[15]);
        this.businessClassPrice = Integer.parseInt(info[16]);
        this.economyClassPrice = Integer.parseInt(info[17]);
        this.firstClassFeatures = info[18];
        this.businessClassFeatures = info[19];
        this.economyClassFeatures = info[20];
        this.firstClassPremiumPoints = Integer.parseInt(info[21]);
        this.businessClassPremiumPoints = Integer.parseInt(info[22]);
        this.economyClassPremiumPoints = Integer.parseInt(info[23]);
        setSeats(seatInfo);
        setPassengers(passengerInfo);
        setTickets(ticketInfo);
        System.out.println("Flight Constructor" + info[7] + " " + info[9] + " " + info[10] + " " + info[11]);

//        expandCountOfSeats(Integer.parseInt(info[7]), Integer.parseInt(info[9]),
//                Integer.parseInt(info[10]), Integer.parseInt(info[11]));
    }

    public void fillRowOfSeats(int rowNumber) {
        for (int i = 0; i < cntTotalSeatCols; i++) {
            if (i < cntFirstClassCols) {
                Seats.get(rowNumber).add(new FirstClass(String.valueOf((char) ('A' + rowNumber)) + String.valueOf(i + 1), true, firstClassPrice, firstClassFeatures));
            } else if (i < cntFirstClassCols + cntBusinessClassCols) {
                Seats.get(rowNumber).add(new BusinessClass(String.valueOf((char) ('A' + rowNumber)) + String.valueOf(i + 1), true, businessClassPrice, businessClassFeatures));
            } else {
                Seats.get(rowNumber).add(new EconomyClass(String.valueOf((char) ('A' + rowNumber)) + String.valueOf(i + 1), true, economyClassPrice, economyClassFeatures));
            }
            System.out.print(Seats.get(rowNumber).get(i).getSeatNumber() + " ");
        }
        System.out.println();
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
        System.out.println(cntTotalSeatRows + " " + newCountOfRows);
        while (this.cntTotalSeatRows < newCountOfRows) {
            Seats.add(new ArrayList<Seat>());
            fillRowOfSeats(cntTotalSeatRows);
            ++this.cntTotalSeatRows;
        }
        System.out.println(cntTotalSeatRows + " " + newCountOfRows);

        System.out.println("cntFirstClassColsBefore " + cntFirstClassCols + " " + newCountOfFirstClass);
        while (this.cntFirstClassCols < newCountOfFirstClass) {
            System.out.println("Size of seats: " + Seats.size());
            for (ArrayList<Seat> row : Seats) {
                System.out.println(row.size());
                row.add(cntFirstClassCols, new FirstClass("", true, firstClassPrice, firstClassFeatures));
            }
            ++this.cntFirstClassCols;
            ++this.cntTotalSeatCols;
        }
        System.out.println("cntFirstClassColsAfter " + cntFirstClassCols + " " + newCountOfFirstClass);

        System.out.println("cntBusinessClassColsBefore " + cntBusinessClassCols + " " + newCountOfBusinessClass);
        while (this.cntBusinessClassCols < newCountOfBusinessClass) {
            for (ArrayList<Seat> row : Seats)
                row.add(cntFirstClassCols + cntBusinessClassCols, new BusinessClass("", true, businessClassPrice, businessClassFeatures));
            ++this.cntBusinessClassCols;
            ++this.cntTotalSeatCols;
        }
        System.out.println("cntBusinessClassColsAfter " + cntBusinessClassCols + " " + newCountOfBusinessClass);

        System.out.println("cntEconomyClassColsBefore " + cntEconomyClassCols + " " + newCountOfEconomyClass);
        while (this.cntEconomyClassCols < newCountOfEconomyClass) {
            for (ArrayList<Seat> row : Seats)
                row.add(cntFirstClassCols + cntBusinessClassCols + cntEconomyClassCols, new EconomyClass("", true, economyClassPrice, economyClassFeatures));
            ++this.cntEconomyClassCols;
            ++this.cntTotalSeatCols;
        }
        System.out.println("cntEconomyClassColsAfter " + cntEconomyClassCols + " " + newCountOfEconomyClass);

        setSeatNumbers();
        return true;
    }

    public boolean isEmptyPassengers() {
        return this.passengers.isEmpty();
    }

    public boolean isEmptyTickets() {
        return this.reservedTickets.isEmpty();
    }

    public void setPassengers(String[] passengers) {
        if (passengers != null && passengers.length > 0)
            for (int i = 0; i < passengers.length; i++) {
                String[] content = passengers[i].split(" ");
                this.passengers.add(new Passenger(content[0], content[1], content[2], content[3]));
            }
    }

    public void setTickets(String[] tickets) {
        if (tickets != null && tickets.length > 0) {
            for (int i = 0; i < tickets.length; i++) {
                String[] content = tickets[i].split(" ");
                this.reservedTickets.add(new Ticket(content[0], content[1], Boolean.parseBoolean(content[2]), Double.parseDouble(content[3]), content[4], content[5], content[6], content[7], content[8]));
            }
        }
    }

    public void setSeats(String[] seats) {
        if (seats != null && seats.length > 0) {
            String[] content = seats[0].split(" ");
//            int totalSeats=Integer.parseInt(content[0]) , cntTotalSeatRows = Integer.parseInt(content[1]) ,
//                    cntTotalSeatCols = Integer.parseInt(content[2]) , cntFirstClassCols=Integer.parseInt(content[3]) ,
//                    cntBusinessClassCols =Integer.parseInt(content[4]), cntEconomyClassCols=Integer.parseInt(content[5]);
            for (int i = 0; i < cntTotalSeatRows; i++) {
                Seats.add(new ArrayList<>());
            }
            System.out.println("Seat file lines : " + seats.length);
            for (int i = 1; i < seats.length; i++) {
                content = seats[i].split(" ");
                String seatNumber = content[0];
                /// a 22
                int colNum = Integer.parseInt(seatNumber.substring(1));
                if (1 <= colNum && colNum <= cntFirstClassCols) {
                    System.out.println(1 + " " + colNum + " " + cntFirstClassCols);
                    Seats.get(seatNumber.charAt(0) - 'A').add(new FirstClass(content[0], Boolean.parseBoolean(content[1]), Integer.parseInt(content[2]), content[3]));
                } else if (cntFirstClassCols + 1 <= colNum && colNum <= cntFirstClassCols + cntBusinessClassCols) {
                    System.out.println(cntFirstClassCols + 1 + " " + colNum + " " + cntFirstClassCols + cntBusinessClassCols);
                    Seats.get(seatNumber.charAt(0) - 'A').add(new BusinessClass(content[0], Boolean.parseBoolean(content[1]), Integer.parseInt(content[2]), content[3]));
                } else
                    Seats.get(seatNumber.charAt(0) - 'A').add(new EconomyClass(content[0], Boolean.parseBoolean(content[1]), Integer.parseInt(content[2]), content[3]));

                System.out.println("Seats loaded successfully");
            }
        } else {
            System.out.println("Seats are empty " + airlineName + " " + flight_number + "in setSeats function");
        }
        for (int i = 0; i < Seats.size(); i++) {
            for (int j = 0; j < Seats.get(i).size(); j++) {
                System.out.print(Seats.get(i).get(j).getSeatNumber() + " ");
            }
            System.out.println();
        }

    }

    @Override
    public String toString() {
        return airlineName + " " + flight_number + " " + departure_airport + " " +
                arrival_airport + " " + departureDate + " " + arrivalDate + " " +
                totalSeats + " " + cntTotalSeatRows + " " + cntTotalSeatCols + " " +
                cntFirstClassCols + " " + cntBusinessClassCols + " " + cntEconomyClassCols + " " +
                cntOfAvailableFirstClassSeats + " " + cntOfAvailableBusinessClassSeats + " " + cntOfAvailableEconomyClassSeats + " " +
                firstClassPrice + " " + businessClassPrice + " " + economyClassPrice + " " +
                firstClassFeatures + " " + businessClassFeatures + " " + economyClassFeatures + " " +
                firstClassPremiumPoints + " " + businessClassPremiumPoints + " " + economyClassPremiumPoints ;
    }

    public ArrayList<String> getPassengersInfo() {
        ArrayList<String> passengerInfo = new ArrayList<>();
        for (Passenger passenger : passengers)
            passengerInfo.add(passenger.toString());
        return passengerInfo;
    }

    public ArrayList<String> getTicketsInfo() {
        ArrayList<String> ticketsInfo = new ArrayList<>();
        for (Ticket ticket : reservedTickets)
            ticketsInfo.add(ticket.toString());
        return ticketsInfo;
    }

    public ArrayList<String> getSeatsInfo() {
        ArrayList<String> seatsInfo = new ArrayList<>();
        seatsInfo.add(totalSeats + " " + cntTotalSeatRows + " " + cntTotalSeatCols + " " + cntFirstClassCols + " " + cntBusinessClassCols + " " + cntEconomyClassCols);
//        for(ArrayList<Seat> row : Seats)
//            for(Seat seat : row)
//                seatsInfo.add(seat.toString());
        for (int i = 0; i < Seats.get(0).size(); i++) {
            for (int j = 0; j < Seats.size(); j++) {
                seatsInfo.add(Seats.get(j).get(i).toString());
            }
        }
        return seatsInfo;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void addTicket(Ticket ticket) {
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

    public int getCntOfCertainSeats(int col) {
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

    public String getAirlineName() {
        return airlineName;
    }

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


    public void setSeatNumbers() {
        for (char rowCharcter = 'A'; rowCharcter < this.cntTotalSeatRows + 'A'; rowCharcter++) {
            for (int i = 0; i < this.cntTotalSeatCols; i++) {
                Seats.get(rowCharcter - 'A').get(i).setSeatNumber(String.valueOf(rowCharcter) + String.valueOf(i + 1));
                System.out.println("Seat info: " + (int) (rowCharcter - 'A') + " " + i + " " + Seats.get(rowCharcter - 'A').get(i).getSeatNumber());
            }
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

    public int getFirstClassPremiumPoints() {
        return firstClassPremiumPoints;
    }

    public int getBusinessClassPremiumPoints() {
        return businessClassPremiumPoints;
    }

    public int getEconomyClassPremiumPoints() {
        return economyClassPremiumPoints;
    }
}