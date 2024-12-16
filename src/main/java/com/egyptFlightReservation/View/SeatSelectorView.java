package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Model.Seat.Seat;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatSelectorView {
    public static Scanner cin = new Scanner(System.in);

    public SeatSelectorView() {
    }

    public void showMap(ArrayList<ArrayList<Seat>> seats, int totalRows, int firstClassCol, int businessClassCol, int economyClassCol,
                        int FirstPrice, int BusinessPrice, int EconomyPrice, int availableFirst,
                        int availableBusiness, int availableEconomy) {

        System.out.println("================================");
        System.out.println("========== Plane Map ==========");
        System.out.println("================================");

        System.out.print("   ");
        for (int i = 0; i < seats.get(0).size(); i++)
            System.out.print(i + 1 + (i + 1 < 10 ? "  " : " "));
        System.out.println();

        for (int i = 0; i < seats.size(); i++) {
            if (i == seats.size() / 2) System.out.println();
            System.out.print((char) (i + 65) + "  ");
            for (int j = 0; j < seats.get(i).size(); j++) {
                System.out.print((seats.get(i).get(j).isAvailabilityStatus() ? "O" : "X") + "  ");
//                System.out.println(seats.get(i).get(j).getSeatNumber() + "  ");
            }
            System.out.println();
        }

        instructionOfSeatSelection(seats, totalRows, firstClassCol, businessClassCol,
                economyClassCol, FirstPrice, BusinessPrice, EconomyPrice,
                availableFirst, availableBusiness, availableEconomy);


    }

    public void instructionOfSeatSelection(ArrayList<ArrayList<Seat>> seats, int totalRows, int firstClassCol, int businessClassCol, int economyClassCol,
                                           int FirstPrice, int BusinessPrice, int EconomyPrice, int availableFirst,
                                           int availableBusiness, int availableEconomy) {
        System.out.println("====== Details of Seat Selection ======");
        System.out.println("=======================================");
        System.out.println("Seat Number ->  row character and column number (eg. A1) ");
        System.out.println("O -> Available Seat");
        System.out.println("X -> Unavailable Seat");
        System.out.println("First Class Seat -> 1 to " + firstClassCol + "\tPrice: " + FirstPrice + "\tAvailable Seats: " + availableFirst);
        // show pros of first class
        try {
            seats.get(0).get(0).showSeatFeatures();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Business  Class Seat -> " + (firstClassCol + 1) + " to " + (businessClassCol + firstClassCol) + "\tPrice: " + BusinessPrice + "\tAvailable Seats: " + availableBusiness);
        // show pros of Business class
        try {
            seats.get(0).get(firstClassCol).showSeatFeatures();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Economy Class Seat -> " + (firstClassCol + businessClassCol + 1) + " to " + (firstClassCol + economyClassCol + businessClassCol) + "\tPrice: " + EconomyPrice + "\tAvailable Seats: " + availableEconomy);
        // show pros of economy class
        try {
            seats.get(0).get(firstClassCol + businessClassCol).showSeatFeatures();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=======================================");
        Menu.show(new String[]{"Booking Seat(s)", "Back to home page"});
    }

    public int selectChoiceFromMenu() {
        return MenuSelector.select("Enter Number", 1, 2);
    }

    public int getCountOfSeat() {
        System.out.print("Enter Seat Count: ");
        return cin.nextInt();
    }

    public String getSeatNumber(int i) {
        System.out.print("Enter Seat Number " + i + ": ");
        return cin.nextLine();
    }


}
