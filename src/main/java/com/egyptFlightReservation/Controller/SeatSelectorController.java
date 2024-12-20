package com.egyptFlightReservation.Controller;

import Tools.myPair;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Flight;
import com.egyptFlightReservation.Model.Passenger;
import com.egyptFlightReservation.Model.Seat.BusinessClass;
import com.egyptFlightReservation.Model.Seat.FirstClass;
import com.egyptFlightReservation.Model.Seat.Seat;
import com.egyptFlightReservation.Model.Ticket;
import com.egyptFlightReservation.View.SeatSelectorView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class SeatSelectorController {
    private SeatSelectorView view;
    private Flight flight;
    public SeatSelectorController(Flight flight) {
        view = new SeatSelectorView();
        this.flight = flight;

    }

    public myPair<Boolean , Flight> selectionProcess() {
        view.showMap(flight.getSeats() , flight.getCntTotalSeatRows() ,flight.getCntFirstClassCols(),
                flight.getCntBusinessClassCols() , flight.getCntEconomyClassCols() , flight.getFirstClassPrice() , flight.getBusinessClassPrice(),
                flight.getEconomyClassPrice() , flight.getCntOfAvailableFirstClassSeats() , flight.getCntOfAvailableBusinessClassSeats() ,
                flight.getCntOfAvailableEconomyClassSeats() , flight.getFirstClassPremiumPoints() , flight.getBusinessClassPremiumPoints() ,
                flight.getEconomyClassPremiumPoints());

        int choice = view.selectChoiceFromMenu();
        if (choice == 1) {
            int countOfSeats = view.getCountOfSeat();
            while (!(1 <= countOfSeats && countOfSeats <= flight.getCntOfAvailableEconomyClassSeats() +
                    flight.getCntOfAvailableFirstClassSeats() + flight.getCntOfAvailableBusinessClassSeats())) {
                System.out.println("Invalid Number of Seats (May be more than available number of flight.getSeats()) , Please Try Again");
                countOfSeats = view.getCountOfSeat();
            }
            ArrayList<String> seatNums = new ArrayList();
            for (int i = 0; i < countOfSeats; ++i) {
                String tmp = view.getSeatNumber(i + 1);
                int isValid = isValidSeatNumber(tmp);
                while (isValid != 1 || seatNums.contains(tmp)) {
                    System.out.println((isValid == -1 ? "Invalid Number " : "Seat is already booked") + "Please Try Again");
                    tmp = view.getSeatNumber(i + 1);
                    isValid = isValidSeatNumber(tmp);
                }
                seatNums.add(tmp);
            }
            //Book or go to home page
            //booking then payment  then return here true of false
            // if true -> make n tickets and record client as passenger and flag the flight.getSeats()
            int totalPrice = 0 , gainedPremiumPoints = 0;
            for (int i = 0; i < seatNums.size(); ++i){
                Seat tmp = flight.getSeats().get(seatNums.get(i).charAt(0) - 'A').get(Integer.parseInt(seatNums.get(i).substring(1)) - 1);
                totalPrice += tmp.getSeatPrice();
                if(tmp instanceof FirstClass)
                    gainedPremiumPoints += flight.getFirstClassPremiumPoints();
                else if(tmp instanceof BusinessClass)
                    gainedPremiumPoints += flight.getBusinessClassPremiumPoints();
                else
                    gainedPremiumPoints += flight.getEconomyClassPremiumPoints();
            }

            BookingController bookingController = new BookingController(flight.getAirlineName(), flight.getFlightNumber(), flight.getDepartureAirport(),
                    flight.getDepartureDate(), flight.getArrivalAirport(),
                    flight.getArrivalDate(), String.valueOf(countOfSeats), totalPrice);

            if (bookingController.process()) {
                //generate the tickets
                for (int i = 0; i < seatNums.size(); ++i) {
                    flight.getSeats().get(seatNums.get(i).charAt(0) - 'A').get(Integer.parseInt(seatNums.get(i).substring(1)) - 1).setAvailabilityStatus(false);
                    //decrease cnt of available flight.getSeats()
                    int colNum = Integer.parseInt(seatNums.get(i).substring(1));

                    if(1 <= colNum && colNum <= flight.getCntFirstClassCols())
                        flight.setCntOfAvailableFirstClassSeats(flight.getCntOfAvailableFirstClassSeats() - 1);
                    else if(flight.getCntFirstClassCols() < colNum && colNum <= flight.getCntFirstClassCols() + flight.getCntBusinessClassCols())
                        flight.setCntOfAvailableBusinessClassSeats(flight.getCntOfAvailableBusinessClassSeats() - 1);
                    else
                        flight.setCntOfAvailableEconomyClassSeats(flight.getCntOfAvailableEconomyClassSeats() - 1);

                    Database.getDatabase().addTicket(flight.getDepartureDate(), flight.getFlightNumber(), new Ticket(Database.getDatabase().getCurUser(),
                            UUID.randomUUID().toString(), true,
                            (int) flight.getSeats().get(seatNums.get(i).charAt(0) - 'A').get(Integer.parseInt(seatNums.get(i).substring(1)) - 1).getSeatPrice(),
                            seatNums.get(i), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getAirlineName(), flight.getFlightNumber()));
                }
                Database.getDatabase().addPremiumPoints(gainedPremiumPoints);
                //generate the passengers in flight
                Database.getDatabase().addPassenger(flight.getFlightNumber(), flight.getDepartureDate());
            }

        }

        return new myPair<Boolean , Flight>(true, flight);
    }

    public int isValidSeatNumber(String num) {/// 1 -> valid , -1 wrong in format , -2 seat is already booked
        if ((num.length() == 2 || num.length() == 3) && num.charAt(0) >= 'A'
                && num.charAt(0) <= (char) (65 + flight.getSeats().size() - 1)
                && Integer.parseInt(num.substring(1)) >= 1 && Integer.parseInt(num.substring(1)) <= flight.getSeats().get(0).size()) {
            return (isValidSeat(num) ? 1 : -2);
        } else {
            return -1;
        }
    }

    public boolean isValidSeat(String num) {
        return flight.getSeats().get(num.charAt(0) - 'A').get(Integer.parseInt(num.substring(1)) - 1).isAvailabilityStatus();
    }
}
