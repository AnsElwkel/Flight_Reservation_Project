package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Generally,
 * We assume that the admin enters correct info and format in the input
 */
public class AdminView {
    public int mainAdminMenu() {
        Scanner cin = new Scanner(System.in);
        Tools.showTableFormat.show("Main Admin Operations" ,
                new String[]{"Choice" , "Operation"} ,
                new String [][] { {"1" , "Add Admin"} ,
                        {"2" , "Add Airport"},
                        {"3" , "Show Airports"},
                        {"4" , "Show Users"},
                        {"5" , "Show Airline Admins"},
                        {"6" , "Log out"}});
        System.out.print("Enter your choice: ");
        return cin.nextInt();
    }

    public void displayAirlineFlights(String [] titles , String[][] data ){
        Tools.showTableFormat.show("Airline Flights" , titles , data);
        Tools.Menu.showMessage("" , 1);
    }

    public String getNewAdminUserName() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Admin Username: ");
        return cin.nextLine();
    }

    public String getNewAdminName() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Admin Name: ");
        return cin.nextLine();
    }

    public String getAdminPassword() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Admin Password: ");
        return cin.nextLine();
    }

    public String getNewAdminEmail() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Admin Email: ");
        return cin.nextLine();
    }

    public String getAirlineName() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Airline Name: ");
        return cin.nextLine();
    }

    public String getAirlineCode() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Airline Code: ");
        return cin.nextLine();
    }

    public String getAirlineLocation() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Airline Location: ");
        return cin.nextLine();
    }

    public String getNewDepartureDate() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Departure Date: ");
        return cin.nextLine();
    }

    public String getNewArrivalDate() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Arrival Date: ");
        return cin.nextLine();
    }

    public String newCountOfRows() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter New Number of Rows (must be between count of current seat and 10 inclusive): ");
        return cin.nextLine();
    }

    public String getNewFirstClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Number of New First Class Cols (must be between current count of first class and 26 inclusive): ");
        return cin.nextLine();
    }

    public String getNewBusinessClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Number of New Business Class Cols (must be between current count of business class and 26 inclusive): ");
        return cin.nextLine();
    }

    public String getNewEconomyClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Number of New Business Class Cols (must be between current count of economy class and 26 inclusive): ");
        return cin.nextLine();
    }

    public int adminMenu() {
        Scanner cin = new Scanner(System.in);
        Tools.showTableFormat.show("Admin Operations" ,
                new String[]{"Choice" , "Operation"} ,
                new String [][] { {"1" , "Add Flight"} ,
                                  {"2" , "Remove Flight"},
                                  {"3" , "Update Schedule"},
                                  {"4" , "Update count of Seats"},
                                  {"5" , "Show Flights"} ,
                                  {"6" , "Log out"} });
        System.out.print("Enter Number in range (1 - 5): ");
        return cin.nextInt();
    }

    public String getFlightNumber() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Flight Number: ");
        return cin.nextLine();
    }

    public ArrayList<String> getInfoOfNewFlight() {
        Scanner cin = new Scanner(System.in);
        ArrayList<String> info = new ArrayList<>();
        System.out.print("Enter Flight ID: ");
        info.add(cin.nextLine());
        System.out.print("Enter Departure Airport Name: ");
        info.add(cin.nextLine());
        System.out.print("Enter Arrival Airport Name: ");
        info.add(cin.nextLine());
        System.out.print("Enter Departure Date: ");
        info.add(cin.nextLine());
        System.out.print("Enter Arrival Date: ");
        info.add(cin.nextLine());
        System.out.print("Enter number of seat rows: ");
        info.add(cin.nextLine());
        System.out.print("Enter number of seat columns: ");
        info.add(cin.nextLine());
        System.out.print("Enter number of first seat columns: ");
        info.add(cin.nextLine());
        System.out.print("Enter price of first seat: ");
        info.add(cin.nextLine());
        System.out.print("Enter number of business seat columns: ");
        info.add(cin.nextLine());
        System.out.print("Enter price of business seat: ");
        info.add(cin.nextLine());
        System.out.print("Enter number of economy seat columns: ");
        info.add(cin.nextLine());
        System.out.print("Enter price of economy seat: ");
        info.add(cin.nextLine());
        System.out.print("Enter First Class Feature: ");
        info.add(cin.nextLine());
        System.out.print("Enter Business Class Feature: ");
        info.add(cin.nextLine());
        System.out.print("Enter Economy Class Feature: ");
        info.add(cin.nextLine());
        System.out.print("Enter Premium Points of First Class: ");
        info.add(cin.nextLine());
        System.out.print("Enter Premium Points of Business Class: ");
        info.add(cin.nextLine());
        System.out.print("Enter Premium Points of Economy Class: ");
        info.add(cin.nextLine());
        return info;
    }

    public ArrayList<String> getInfoOfNewAirport() {
        Scanner cin = new Scanner(System.in);
        ArrayList<String> info = new ArrayList<>();
        System.out.print("Enter Airport Name: ");
        info.add(cin.nextLine());
        System.out.print("Enter Airport Code: ");
        info.add(cin.nextLine());
        System.out.print("Enter Airport Location: ");
        info.add(cin.nextLine());
        return info;
    }

    public int getChoiceOfShowAirports(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter 1 to remove certain airport or any number to go back: ");
        return cin.nextInt();
    }
    public int getChoiceOfShowAirlineAdmins(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter 1 to remove certain admin or any number to go back: ");
        return cin.nextInt();
    }
    public int getChoiceOfShowClients(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter 1 to remove certain client or any number to go back: ");
        return cin.nextInt();
    }

    public String getAirportCode(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Airport Code: ");
        return cin.nextLine();
    }
    public String getAdminUsername(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Admin Username Code: ");
        return cin.nextLine();
    }
    public String getClientUsername(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Client Username Code: ");
        return cin.nextLine();
    }
}
