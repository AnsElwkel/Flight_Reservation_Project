package com.egyptFlightReservation.Controller;
import com.egyptFlightReservation.Model.*;
import Tools.myPair;
import com.egyptFlightReservation.View.SearchingView;
import com.egyptFlightReservation.Model.Database;

import java.util.ArrayList;

public class SearchingController {
    private String departureAirport , arrivalAirport , fromDate , toDate ;
    private SearchingView view ;
    private ArrayList<Flight> searchResults ;
    //take sort by choice
    public SearchingController() {
        view = new SearchingView();
        searchResults = new ArrayList<>();
    }
    public void searchProcess() throws Exception{
        this.arrivalAirport = view.getArrivalAirportName();
        this.departureAirport = view.getDepartureAirportName();
        this.toDate = view.getToDate();
        this.fromDate = view.getFromDate();
//        this.searchResults = Database.getDatabase(). // database access
        //filter the array only that between from to date
        view.showResult(searchResults);
        int choice = view.askToSortOrSelectFlight();
        if(choice == 1){
            choice = view.sortBy();
            if(choice == 1){
                //sort by date
            }else if(choice == 2){
                //sort by price
            }else{
                //sort by Seat Available
            }
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





    }
}