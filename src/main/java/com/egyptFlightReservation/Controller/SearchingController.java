package com.egyptFlightReservation.Controller;
import OurExceptions.RangeException;
import com.egyptFlightReservation.Model.*;
import Tools.myPair;
import com.egyptFlightReservation.View.SearchingView;
import com.egyptFlightReservation.Model.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class SearchingController {
    private String departureAirport , arrivalAirport ;
    LocalDate fromDate , toDate ;
    private SearchingView view ;
    private ArrayList<Flight> searchResults ;
    //take sort by choice
    public SearchingController() {
        view = new SearchingView();
        searchResults = new ArrayList<>();
    }

    public void searchProcess() throws Exception{
        this.departureAirport = view.getDepartureAirportName();
        this.arrivalAirport = view.getArrivalAirportName();

        this.toDate = view.getToDate();
        while(toDate == null){
            System.out.println("invalid format");
            this.toDate = view.getToDate();
        }
        this.fromDate = view.getFromDate();
        while(fromDate == null){
            System.out.println("invalid format");
            this.fromDate = view.getFromDate();
        }

        /// Return filtered flights in toDate from fromDate range
        this.searchResults = filterFuntion(); // database access

        if(this.searchResults.isEmpty()){
            System.out.println("No flights found in searchProcess function in searchingController");
            return ;
        }

        this.showResult(searchResults);

        int choice = view.askToSortOrSelectFlight();

        while(choice < 1 || choice > 3){
            System.out.println("Invalid choice");
            choice = view.askToSortOrSelectFlight();
        }

        if(choice == 1){
            this.sortBy(view.sortBy());
            if(choice == 1){
                this.searchResults = filterFuntion();
            }else if(choice == 2){
                sortByPrice(this.searchResults);
            }else if(choice == 3){
                sortByAvailableSeats(this.searchResults);
            }
            showResult(searchResults);
        }
            //call sorting process
        else if(choice == 2) {
            choice = view.getChoiceOfFlight(1 , searchResults.size()); // from 1 to size of array
            //
            if(choice!=-1){
                SeatSelectorController seatController = new SeatSelectorController(searchResults.get(choice).seats);
                seatController.selectionProcess();
            }
        }


        //show sorted by or choosing flight ?
        //take this input and call the function select flight or sort by depend on input
        //sort by -> 1- date , cost , count of available seats
        //select flight -> take the input and (index of flight in array) and call the seat selection
        //after seat selection process go to booking or -1 to back to home page
    }

    public void showResult(ArrayList<Flight> searchResults) {
        if(searchResults.isEmpty()){
            System.out.println("No Suitable Flights found");
        }else{
            String[] results = new String[(int)searchResults.size()];

            for(int i = 0;i < searchResults.size();i++)
                    results[i] = makeResultFormat(searchResults.get(i));


            view.showResult(results);
        }

    }
    public String makeResultFormat(Flight flight){
        String ret = "Airline: " + flight.getAirlineName() + "\n";
        ret += "Departure Date: " + flight.getDepartureDate() + "\n";
        ret += "Arrival Date: " + flight.getArrivalDate() + "\n";
        ret += "Count of available first class seats: " + flight.getCntOfCertainSeats(flight.getCntFirstClassCols()) + '\n';
        ret += "First class seat price: " + flight.getFirstClassPrice() + "\n";
        ret += "Count of available business class seats: " + flight.getCntOfCertainSeats(flight.getCntBusinessClassCols()) + '\n';
        ret += "Business class seat price: " + flight.getBusinessClassPrice() + "\n";
        ret += "Count of available economy class seats: " + flight.getCntOfCertainSeats(flight.getCntEconomyClassCols()) + '\n';
        ret += "Economy class seat price: " + flight.getEconomyClassPrice() + "\n";
        return ret;
    }
    public void sortBy(int choice){
        if(choice == 1){
            this.searchResults = filterFuntion();
            showResult(searchResults);
        }else if(choice == 2){
            // sort based on price

            this.searchResults.sort(Comparator.comparing(Flight::getFirstClassPrice));
            this.searchResults.sort(Comparator.comparing(Flight::getBusinessClassPrice));
            this.searchResults.sort(Comparator.comparing(Flight::getEconomyClassPrice));


        }else if(choice == 3){
            // sort based on cnt of available seat

        }
    }

    public ArrayList<Flight> filterFuntion() {
        return Database.getDatabase().searchFlights(toDate , fromDate);
    }

    public void sortByPrice(ArrayList<Flight> flights) throws RangeException {
        int choice = view.getChoiceOfSortByPriceOfAnyClass();
        while(choice < 1 || choice > 3){
            System.out.println("Invalid choice");
            choice = view.getChoiceOfSortByPriceOfAnyClass();
        }
        if(choice == 1)
            flights.sort(Comparator.comparing(Flight::getFirstClassPrice));
        else if(choice == 2)
            flights.sort(Comparator.comparing(Flight::getBusinessClassPrice));

        else if(choice == 3)
            flights.sort(Comparator.comparing(Flight::getEconomyClassPrice));
    }
    public void sortByAvailableSeats(ArrayList<Flight> flights) throws RangeException {
        int choice = view.getChoiceOfSortByAvailableOfAnyClass();
        while(choice < 1 || choice > 3){
            System.out.println("Invalid choice");
            choice = view.getChoiceOfSortByAvailableOfAnyClass();
        }
        if(choice == 1)
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableFirstClassSeats).reversed());
        else if(choice == 2)
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableBusinessClassSeats).reversed());
        else if(choice == 3)
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableEconomyClassSeats).reversed());
    }
}