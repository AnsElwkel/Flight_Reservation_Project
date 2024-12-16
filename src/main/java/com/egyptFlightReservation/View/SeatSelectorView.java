package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Model.Seat.Seat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * showMap ->
 * <p>
 * First class from 1 to 3
 * Second class from 4 to 10
 * Economy class from 11 to 15
 * <p>
 * 1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 * A  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 * B  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 * C  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 * <p>
 * D  O  O  O  X  O  O  O  O  O  O  O  O  O  O  O
 * E  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 * F  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 * <p>
 * showInfoOfEachClass->
 * to show detail of seat and price
 * seats[][].displayAdvantage() first
 * seats[][].displayAdvantage() business
 * seats[][].displayAdvantage() economy
 * <p>
 * selectSeat ->
 * enter the row character and column number to select the seat or -1 to cancel
 */
public class SeatSelectorView {
    public static Scanner cin = new Scanner(System.in);

    public SeatSelectorView() {
    }

    public void showMap(ArrayList<ArrayList<Seat>> seats, int totalRows, int firstClassCol, int businessClassCol, int economyClassCol,
                        int FirstPrice, int BusinessPrice, int EconomyPrice, int availableFirst,
                        int availableBusiness, int availableEconomy) {

        /**Map Problem
         * first x colmun is first class
         * second y colmun is business class
         * third z colmun is economy class
         *
         *
         * rows must be even number to divisible by 2
         * and _MAX_ROWS_ = 10 and _MIN_ROW_ = 4
         *
         * _MAX_COLUMN_ = 26 and  _MIN_COLMUN_ = 10
         *
         * First class from 1 to 3
         * Second class from 4 to 10
         * Economy class from 11 to 15
         *
         *    1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
         * A  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * B  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * C  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         *
         * D  O  O  O  X  O  O  O  O  O  O  O  O  O  O  O
         * E  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * F  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * */
        //

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
