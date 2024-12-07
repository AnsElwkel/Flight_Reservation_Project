package com.egyptFlightReservation.View;

import OurExceptions.RangeException;
import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Model.Flight;

import java.util.ArrayList;

public class SearchingView {
    public SearchingView(){
        System.out.println("Searching view");
    }
    public String getDepartureAirportName() {
        System.out.println("Enter Departure Airport: ");
        return Input.cin.nextLine();
    }

    public String getArrivalAirportName() {
        System.out.println("Enter Arrival Airport: ");
        return Input.cin.nextLine();
    }

    public String getToDate() {
        System.out.println("Enter To Date: ");
        return Input.cin.nextLine();
    }

    public String getFromDate() {
        System.out.println("Enter From Date: ");
        return Input.cin.nextLine();
    }

    public void showResult(ArrayList<Flight> flights) {
        for (Flight flight : flights) {
            // for each flight show flight details in menu format and take input of choosing or -1 to canceling
        }
    }
    public int askToSortOrSelectFlight() throws RangeException {
        Menu.show(new String[]{"Sort" , "Select Flight"});
        return MenuSelector.select("Enter The Choice" , 1 , 2);
    }

    public int getChoiceOfFlight(int from , int to) throws RangeException {
        return MenuSelector.select("Enter Choice of Flight" , from , to);
    }

    public int sortBy() throws RangeException {
        Menu.show(new String[]{"Date" , "Price" , "Seat Available"});
        return MenuSelector.select("Enter The Choice" , 1 , 3);
    }

}
