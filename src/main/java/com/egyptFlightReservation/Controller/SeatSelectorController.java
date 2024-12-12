package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Passenger;
import com.egyptFlightReservation.Model.Seat.Seat;
import com.egyptFlightReservation.Model.Ticket;
import com.egyptFlightReservation.View.SeatSelectorView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class SeatSelectorController {
    private SeatSelectorView view;
    ArrayList<ArrayList<Seat>> seats;
    private int totalRows , cntOfFirst , cntOfBusiness ,
                CntOfEconomy,FirstPrice , BusinessPrice , EconomyPrice,
                availableFirst , availableBusiness , availableEconomy;
    private String airlineName, flightNumber;
    private String departureAirport,arrivalAirport ;
    private LocalDate departureDate ,arrivalDate;
    public SeatSelectorController(String airlineName , String flightNumber , ArrayList<ArrayList<Seat>> seats, int totalRows, int cntOfFirst , int cntOfBusiness , int cntOfEconomy,
                                  int FirstPrice , int BusinessPrice , int EconomyPrice, int availableFirst,
                                  int availableBusiness , int availableEconomy, String departureAirport
                                   , String arrivalAirport ,LocalDate departureDate,
                                  LocalDate arrivalDate){
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.seats = seats;
        this.totalRows = totalRows;
        this.cntOfFirst = cntOfFirst;
        this.cntOfBusiness = cntOfBusiness;
        this.CntOfEconomy = cntOfEconomy;
        this.FirstPrice = FirstPrice;
        this.BusinessPrice = BusinessPrice;
        this.EconomyPrice = EconomyPrice;
        this.availableFirst = availableFirst;
        this.availableBusiness = availableBusiness;
        this.availableEconomy = availableEconomy;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;

        view = new SeatSelectorView();
    }

    public boolean selectionProcess(){
        view.showMap(seats, totalRows, cntOfFirst ,
                     cntOfBusiness , CntOfEconomy,
                     FirstPrice , BusinessPrice , EconomyPrice,
                     availableFirst , availableBusiness , availableEconomy);

        int choice = view.selectChoiceFromMenu();
        if(choice == 1){
            int countOfSeats = view.getCountOfSeat();
            while(!(1 <= countOfSeats &&  countOfSeats <= availableEconomy + availableFirst + availableBusiness) ){
                System.out.println("Invalid Number of Seats (May be more than available number of seats) , Please Try Again");
                countOfSeats = view.getCountOfSeat();
            }
            ArrayList<String> seatNums = new ArrayList();
            for(int i = 0 ; i < countOfSeats ; ++i){
                String tmp = view.getSeatNumber(i+1);
                int isValid = isValidSeatNumber(tmp);
                while(isValid != 1 || seatNums.contains(tmp)){
                    System.out.println( (isValid == -1 ? "Invalid Number " : "Seat is already booked") + "Please Try Again");
                    tmp = view.getSeatNumber(i+1);
                    isValid = isValidSeatNumber(tmp);
                }
                seatNums.add(tmp);
            }
            //Book or go to home page
            //booking then payment  then return here true of false
            // if true -> make n tickets and record client as passenger and flag the seats
            int totalPrice = 0;
            for(int i = 0; i < seatNums.size(); ++i)
                totalPrice =seats.get(seatNums.get(i).charAt(0) - 'A').get(seatNums.get(i).charAt(1) - '1').getSeatPrice();
            BookingController bookingController = new BookingController(airlineName ,flightNumber , departureAirport,
                                                            departureDate , arrivalAirport ,
                                                            arrivalDate , String.valueOf(countOfSeats) , totalPrice);
            if(bookingController.process()){
                //generate the tickets
                for(int i = 0 ; i < seatNums.size() ; ++i){
                    Database.getDatabase().addTicket(departureDate , flightNumber , new Ticket(Database.getDatabase().getCurUser(), UUID.randomUUID().toString() , true , (int)seats.get(seatNums.get(i).charAt(0) - 'A').get(seatNums.get(i).charAt(1) - '1').getSeatPrice(), seatNums.get(i),departureAirport,arrivalAirport , airlineName,flightNumber ) );
                }
                //generate the passengers in flight
                Database.getDatabase().addPassenger(flightNumber , departureDate);
            }

        }else{
            // return to home page


        }


        return true;
    }
    public int isValidSeatNumber(String num) {/// 1 -> valid , -1 wrong in format , -2 seat is already booked
        if(num.length() == 2 && num.charAt(0) >= 'A'
                && num.charAt(0) <= (char)(65+seats.size()-1)
                && num.charAt(1) >= '1' && num.charAt(1) <= (char)('1'+seats.get(0).size() - 1)){
            return (isValidSeat(num) ? 1 : -2);
        }else{
            return -1;
        }
    }
    public boolean isValidSeat(String num){
        return seats.get(num.charAt(0) - 'A').get(num.charAt(1) - '1').isAvailabilityStatus();
    }
}
