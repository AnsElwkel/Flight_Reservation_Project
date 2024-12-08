package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;

import java.util.ArrayList;
/**
 * Generally,
 * We assume that the admin enters correct info and format in the input
 * */
public class AdminView {
    public int mainAdminMenu(){
        Menu.show(new String[]{"Add Admin" , "Log out"});
        return Input.cin.nextInt();
    }
    public String getNewAdminUserName(){
        System.out.println("Enter New Admin Username: ");
        return Input.cin.nextLine();
    }
    public String getNewAdminName(){
        System.out.println("Enter New Admin Name: ");
        return Input.cin.nextLine();
    }
    public String getAdminPassword(){
        System.out.println("Enter New Admin Password: ");
        return Input.cin.nextLine();
    }
    public String getNewAdminEmail(){
        System.out.println("Enter New Admin Email: ");
        return Input.cin.nextLine();
    }
    public String getAirlineName(){
        System.out.println("Enter New Airline Name: ");
        return Input.cin.nextLine();
    }
    public String getAirlineCode(){
        System.out.println("Enter New Airline Code: ");
        return Input.cin.nextLine();
    }
    public String getAirlineLocation(){
        System.out.println("Enter New Airline Location: ");
        return Input.cin.nextLine();
    }
    public String getNewDepartureDate(){
        System.out.println("Enter New Departure Date: ");
        return Input.cin.nextLine();
    }
    public String getNewArrivalDate(){
        System.out.println("Enter New Arrival Date: ");
        return Input.cin.nextLine();
    }
    public String newCountOfRows(){
        System.out.println("Enter New Number of Rows (must be between count of current seat and 10 inclusive): ");
        return Input.cin.nextLine();
    }
    public String getNewFirstClassCols(){
        System.out.println("Enter Number of New First Class Cols (must be between current count of first class and 26 inclusive): ");
        return Input.cin.nextLine();
    }
    public String getNewBusinessClassCols(){
        System.out.println("Enter Number of New First Class Cols (must be between current count of business class and 26 inclusive): ");
        return Input.cin.nextLine();
    }
    public String getNewEconomyClassCols(){
        System.out.println("Enter Number of New First Class Cols (must be between current count of economy class and 26 inclusive): ");
        return Input.cin.nextLine();
    }

    public int adminMenu() {
        Menu.show(new String[]{"Add Flight", "Remove Flight", "Update Schedule",
                "Update count of seats", "Log out"});
        return Input.cin.nextInt();
    }
    public String getFlightNumber(){
        System.out.println("Enter Flight Number: ");
        return Input.cin.nextLine();
    }

    public ArrayList<String> getInfoOfNewFlight() {
        ArrayList<String> info = new ArrayList<>();
        System.out.print("Enter Flight ID: ");
        info.add(Input.cin.nextLine());
        System.out.print("Enter Departure Airport Name: ");
        info.add(Input.cin.nextLine());
        System.out.print("Enter Arrival Airport Name: ");
        info.add(Input.cin.nextLine());
        System.out.print("Enter Departure Date: ");
        info.add(Input.cin.nextLine());
        System.out.print("Enter Arrival Date: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter number of seat rows: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter number of seat columns: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter number of first seat columns: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter price of first seat: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter number of business seat columns: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter price of business seat: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter number of economy seat columns: ");
        info.add(Input.cin.nextLine());
        System.out.println("Enter price of economy seat: ");
        info.add(Input.cin.nextLine());
        return info;
    }

}
