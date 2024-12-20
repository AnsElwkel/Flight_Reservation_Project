package com.egyptFlightReservation.View;

import OurExceptions.RangeException;
import Tools.DateValidation;
import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Model.Flight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchingView {
    public static Scanner cin = new Scanner(System.in);

    public SearchingView() {
        System.out.println("========================");
        System.out.println("=== Flight Searching ===");
        System.out.println("========================");
    }

    public LocalDate getToDate() {
        return DateValidation.getDate("Enter To Date : ");
    }

    public LocalDate getFromDate() {
        return DateValidation.getDate("Enter From Date : ");
    }

    public void showResult(String[] titles, String[][] resultFlights) {
//        Menu.show(resultFlights);
        if (resultFlights.length == 0) {
            System.out.println("No Result Found!");
            return;
        }
        Tools.showTableFormat.show("Available Flights", titles, resultFlights);
    }

    public int askToSortOrSelectFlight() {
        Menu.show(new String[]{"Sort", "Select Flight", "Back to home page"});
        return MenuSelector.select("Enter Number", 1, 3);
    }

    public int getChoiceOfFlight(int from, int to) {
        return MenuSelector.select("Enter Choice of Flight", from, to);
    }

    public int sortBy() {
        Menu.show(new String[]{"Date", "Price", "Seat Available" , "Premium Points" , "Rating Average"});
        return MenuSelector.select("Enter The Choice", 1, 5);
    }

    public int getChoiceOfSortByPriceOfAnyClass() {
        Menu.show(new String[]{"FirstClassPrice", "BusinessClassPrice", "EconomyClassPrice"});
        return MenuSelector.select("Enter The Choice", 1, 3);
    }

    public int getChoiceOfSortByAvailableOfAnyClass() {
        Menu.show(new String[]{"AvailableFirstClass", "AvailableBusinessClass", "AvailableEconomyClass"});
        return MenuSelector.select("Enter The Choice", 1, 3);
    }
    public int getChoiceOfSortByPremiumPoints() {
        Menu.show(new String[]{"FirstClassPremiumPoints", "BusinessClassPremiumPoints", "EconomyClassPremiumPoints"});
        return MenuSelector.select("Enter The Choice", 1, 3);
    }

}
