package com.egyptFlightReservation.Controller;

import Tools.myPair;
import com.egyptFlightReservation.Model.*;
import com.egyptFlightReservation.View.SearchingView;
import com.egyptFlightReservation.Model.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class SearchingController {

    LocalDate fromDate, toDate;
    private SearchingView view;
    private ArrayList<Flight> searchResults;

    //take sort by choice
    public SearchingController() {
        view = new SearchingView();
        searchResults = new ArrayList<>();
    }

    public boolean searchProcess(int op) {
        if(op == 1){
            this.fromDate = view.getFromDate();
            while (fromDate == null) {
                System.out.println("invalid format");
                this.fromDate = view.getFromDate();
            }

            this.toDate = view.getToDate();
            while (toDate == null) {
                System.out.println("invalid format");
                this.toDate = view.getToDate();
            }
        }else if(op == 2){
            this.fromDate = LocalDate.now();
            this.toDate = LocalDate.now().plusYears(2);
        }

        /// Return filtered flights in toDate from fromDate range
        this.searchResults = filterFunction(); // database access

        if (this.searchResults.isEmpty()) {
            System.out.println("No flights found in searchProcess function in searchingController");
            return false;
        }

        if (!selectionAfterShowResult()) return false;

        return true;
    }

    public boolean selectionAfterShowResult() { /// recursive function
        this.showResult(searchResults);

        int choice = view.askToSortOrSelectFlight();

        while (!(1 <= choice && choice <= 3)) {
            System.out.println("Invalid choice");
            choice = view.askToSortOrSelectFlight();
        }

        if (choice == 1) {
            choice = view.sortBy();
            this.sortBy(choice);
            selectionAfterShowResult(); /// recursion call

        } else if (choice == 2) {
            choice = view.getChoiceOfFlight(Math.min(1 , searchResults.size()), searchResults.size()); // from 1 to size of array
            --choice;
            SeatSelectorController seatController = new SeatSelectorController(searchResults.get(choice));
            myPair<Boolean , Flight> selectionProcessRet = seatController.selectionProcess();
            if (!selectionProcessRet.getFirst()) return false;
            searchResults.set(choice , selectionProcessRet.getSecond());
            return true;
        } else {
            return false;
        }
        return true;
    }


    public void showResult(ArrayList<Flight> searchResults) {
        if (searchResults.isEmpty()) {
            System.out.println("No Suitable Flights found");
        } else {
            String[] titles = {"Number", "AirlineName" , "Rating Average" , "Rating Counts", "Dep. Date", "Arr. Date",
                    "First Class Cnt", "First Class Price" , "First Class Premium Points", "Business Class Cnt", "Business Class Price", "Business Class Premium Points",
                    "Economy Class Cnt", "Economy Class Price" , "Economy Class Premium Points"};
            String[][] results = new String[(int) searchResults.size()][];

            for (int i = 0; i < searchResults.size(); i++)
                results[i] = makeResultFormat(i + 1, searchResults.get(i));
            view.showResult(titles, results);
        }
        Tools.Menu.showMessage("" , 1);

    }

    public String[] makeResultFormat(int number, Flight flight) { ///  change this to table format
        String[] ret = {String.valueOf(number), flight.getAirlineName(),String.valueOf(Database.getDatabase().getRatingAverageOfCertainAirline(flight.getAirlineName())) ,
                String.valueOf(Database.getDatabase().getRatingCountOfCertainAirline(flight.getAirlineName())), flight.getDepartureDate().toString(), flight.getArrivalDate().toString(),
                String.valueOf(flight.getCntOfAvailableFirstClassSeats()), String.valueOf(flight.getFirstClassPrice()), String.valueOf(flight.getFirstClassPremiumPoints()),
                String.valueOf(flight.getCntOfAvailableBusinessClassSeats()), String.valueOf(flight.getBusinessClassPrice()), String.valueOf(flight.getBusinessClassPremiumPoints()),
                String.valueOf(flight.getCntOfAvailableEconomyClassSeats()), String.valueOf(flight.getEconomyClassPrice()) , String.valueOf(flight.getEconomyClassPremiumPoints()),
        };
        return ret;
    }

    public void sortBy(int choice) {
        if (choice == 1) { /// Sort by Date
            this.searchResults = filterFunction();
        } else if (choice == 2) { /// Sort based on price
            sortByPrice(searchResults);
        } else if (choice == 3) { /// Sort based on cnt of available seat
            sortByAvailableSeats(searchResults);
        } else if(choice == 4) {
            sortByPremiumPoints(searchResults);
        }else if(choice == 5) {
            searchResults = sortByRatingAverage(searchResults);
        }
    }

    public ArrayList<Flight> filterFunction() {
        return Database.getDatabase().searchFlights(fromDate, toDate);
    }

    public ArrayList<Flight> sortByRatingAverage(ArrayList<Flight> flights) {
        ArrayList<Flight> sortedFlights = new ArrayList<>();
        TreeMap<Double , ArrayList<Flight>> map = new TreeMap<>();
        for (Flight flight : flights) {
            double avg = Database.getDatabase().getRatingAverageOfCertainAirline(flight.getAirlineName());
            if(!map.containsKey(avg))
                map.put(avg , new ArrayList<>());
            map.get(avg).add(flight);
        }

        for (ArrayList<Flight> flight : map.values()) {
            sortedFlights.addAll(flight);
        }
        sortedFlights = new ArrayList<Flight>(sortedFlights.reversed());
        return sortedFlights;
    }

    public void sortByPrice(ArrayList<Flight> flights) {
        int choice = view.getChoiceOfSortByPriceOfAnyClass();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice");
            choice = view.getChoiceOfSortByPriceOfAnyClass();
        }
        if (choice == 1)
            flights.sort(Comparator.comparing(Flight::getFirstClassPrice));
        else if (choice == 2)
            flights.sort(Comparator.comparing(Flight::getBusinessClassPrice));
        else flights.sort(Comparator.comparing(Flight::getEconomyClassPrice));
    }

    public void sortByAvailableSeats(ArrayList<Flight> flights) {///  check logic again
        int choice = view.getChoiceOfSortByAvailableOfAnyClass();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice");
            choice = view.getChoiceOfSortByAvailableOfAnyClass();
        }
        for (int i = 0; i < flights.size(); i++) {
            System.out.println("Cnt Of Available Seats " + flights.get(i).getCntOfAvailableFirstClassSeats() + " " + flights.get(i).getCntOfAvailableBusinessClassSeats() + " " + flights.get(i).getCntOfAvailableEconomyClassSeats());
        }
        if (choice == 1)
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableFirstClassSeats).reversed()); ///debug
        else if (choice == 2)
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableBusinessClassSeats).reversed()); ///debug
        else
            flights.sort(Comparator.comparing(Flight::getCntOfAvailableEconomyClassSeats).reversed());
    }
    public void sortByPremiumPoints(ArrayList<Flight> flights) {///  check logic again
        int choice = view.getChoiceOfSortByPremiumPoints();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice");
            choice = view.getChoiceOfSortByPremiumPoints();
        }
        if (choice == 1)
            flights.sort(Comparator.comparing(Flight::getFirstClassPremiumPoints).reversed()); ///debug
        else if (choice == 2)
            flights.sort(Comparator.comparing(Flight::getBusinessClassPremiumPoints).reversed()); ///debug
        else
            flights.sort(Comparator.comparing(Flight::getEconomyClassPremiumPoints).reversed());
    }
}