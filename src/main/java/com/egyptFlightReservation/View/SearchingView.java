package com.egyptFlightReservation.View;

import OurExceptions.RangeException;
import Tools.DateValidation;
import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Model.Flight;

import java.time.LocalDate;
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

    public LocalDate getToDate() {
        return DateValidation.getDate("Enter To Date: ");
    }

    public LocalDate getFromDate() {
        return DateValidation.getDate("Enter From Date: ");
    }

    public void showResult(String[] resultFlights) {
        Menu.show(resultFlights);
    }
    public int askToSortOrSelectFlight() throws RangeException {
        Menu.show(new String[]{"Sort" , "Select Flight" , "Back to home page"});
        return MenuSelector.select("Enter The Choice" , 1 , 2);
    }

    public int getChoiceOfFlight(int from , int to) throws RangeException {
        return MenuSelector.select("Enter Choice of Flight" , from , to);
    }

    public int sortBy() throws RangeException {
        Menu.show(new String[]{"Date" , "Price" , "Seat Available"});
        return MenuSelector.select("Enter The Choice" , 1 , 3);
    }
    public int getChoiceOfSortByPriceOfAnyClass()throws RangeException {
        Menu.show(new String[]{"FirstClassPrice" , "BusinessClassPrice" , "EconomyClassPrice"});
        return MenuSelector.select("Enter The Choice" , 1 , 3);
    }
    public int getChoiceOfSortByAvailableOfAnyClass()throws RangeException {
        Menu.show(new String[]{"AvailableFirstClassPrice" , "AvailableBusinessClass" , "AvailableEconomyClassPrice"});
        return MenuSelector.select("Enter The Choice" , 1 , 3);
    }

}
