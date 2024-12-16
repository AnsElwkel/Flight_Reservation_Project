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
        Menu.show(new String[]{"Add Admin", "Add Airport", "Log out"});
        System.out.println("Enter your choice: ");
        return cin.nextInt();
    }

    public String getNewAdminUserName() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Admin Username: ");
        return cin.nextLine();
    }

    public String getNewAdminName() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Admin Name: ");
        return cin.nextLine();
    }

    public String getAdminPassword() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Admin Password: ");
        return cin.nextLine();
    }

    public String getNewAdminEmail() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Admin Email: ");
        return cin.nextLine();
    }

    public String getAirlineName() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Airline Name: ");
        return cin.nextLine();
    }

    public String getAirlineCode() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Airline Code: ");
        return cin.nextLine();
    }

    public String getAirlineLocation() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Airline Location: ");
        return cin.nextLine();
    }

    public String getNewDepartureDate() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Departure Date: ");
        return cin.nextLine();
    }

    public String getNewArrivalDate() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Arrival Date: ");
        return cin.nextLine();
    }

    public String newCountOfRows() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter New Number of Rows (must be between count of current seat and 10 inclusive): ");
        return cin.nextLine();
    }

    public String getNewFirstClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Number of New First Class Cols (must be between current count of first class and 26 inclusive): ");
        return cin.nextLine();
    }

    public String getNewBusinessClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Number of New Business Class Cols (must be between current count of business class and 26 inclusive): ");
        return cin.nextLine();
    }

    public String getNewEconomyClassCols() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Number of New Business Class Cols (must be between current count of economy class and 26 inclusive): ");
        return cin.nextLine();
    }

    public int adminMenu() {
        Scanner cin = new Scanner(System.in);
        Menu.show(new String[]{"Add Flight", "Remove Flight", "Update Schedule",
                "Update count of seats", "Log out"});
        System.out.println("Enter Number in range (1 - 5): ");
        return cin.nextInt();
    }

    public String getFlightNumber() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Flight Number: ");
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
        System.out.println("Enter number of seat rows: ");
        info.add(cin.nextLine());
        System.out.println("Enter number of seat columns: ");
        info.add(cin.nextLine());
        System.out.println("Enter number of first seat columns: ");
        info.add(cin.nextLine());
        System.out.println("Enter price of first seat: ");
        info.add(cin.nextLine());
        System.out.println("Enter number of business seat columns: ");
        info.add(cin.nextLine());
        System.out.println("Enter price of business seat: ");
        info.add(cin.nextLine());
        System.out.println("Enter number of economy seat columns: ");
        info.add(cin.nextLine());
        System.out.println("Enter price of economy seat: ");
        info.add(cin.nextLine());
        System.out.println("Enter First Class Feature: ");
        info.add(cin.nextLine());
        System.out.println("Enter Business Class Feature: ");
        info.add(cin.nextLine());
        System.out.println("Enter Economy Class Feature: ");
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
}
