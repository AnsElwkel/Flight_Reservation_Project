package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import Tools.Input;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Flight;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.View.AdminView;
import com.egyptFlightReservation.View.FirstView;

import java.util.ArrayList;

public class AdminController {
    Admin admin;
    private AdminView view;

    public AdminController() {
        admin = Database.getDatabase().getAdmin();
        this.view = new AdminView();
    }

    public void MainAdminFunction() {
        int choice;
        do {
            choice = view.mainAdminMenu();
            if (choice == 1)
                addNewAdminProcess();
        } while (choice != 1);

        try {
            FirstView.Run();
        } catch (RangeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewAdminProcess() {
        String adminUserName = view.getNewAdminUserName();
        while (Input.isContainSpaces(adminUserName)) {
            System.out.println("Enter the input without spaces");
            adminUserName = view.getNewAdminUserName();
        }
        String adminName = view.getNewAdminName();
        while (Input.isContainSpaces(adminName)) {
            System.out.println("Enter the input without spaces");
            adminName = view.getNewAdminName();
        }
        String adminEmail = view.getNewAdminEmail();
        while (Input.isContainSpaces(adminEmail)) {
            System.out.println("Enter the input without spaces");
            adminEmail = view.getNewAdminEmail();
        }
        String adminPassword = view.getAdminPassword();
        while (Input.isContainSpaces(adminPassword)) {
            System.out.println("Enter the input without spaces");
            adminPassword = view.getAdminPassword();
        }
        String airlineName = view.getAirlineName();
        while (Input.isContainSpaces(airlineName)) {
            System.out.println("Enter the input without spaces");
            airlineName = view.getAirlineName();
        }
        String airlineCode = view.getAirlineCode();
        while (Input.isContainSpaces(airlineCode)) {
            System.out.println("Enter the input without spaces");
            airlineCode = view.getAirlineCode();
        }
        String airlineLocation = view.getAirlineLocation();
        while (Input.isContainSpaces(airlineLocation)) {
            System.out.println("Enter the input without spaces");
            airlineLocation = view.getAirlineLocation();
        }

        Admin newAdmin = new Admin(adminUserName, adminName, adminPassword, adminEmail, airlineName);
        Database.getDatabase().saveAdmin(adminName, newAdmin, airlineName, airlineCode , airlineLocation);
    }

    public void AdminProcess() throws RangeException {
        int choice ;
        do{
            choice=view.adminMenu();
            while (!((1 <= choice && choice <= 6))) {
                System.out.println("Enter number in range 1 - 5");
                choice = view.adminMenu();
            }
            if (choice == 1)
                addFlight();
            else if (choice == 2)
                removeFlight();
            else if (choice == 3)
                updateSchedule();
            else if (choice == 4)
                expandCountOfSeats();

        }while(choice!=5);
        //log out
        try {
            FirstView.Run();
        } catch (RangeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addFlight(){
        ArrayList info = view.getInfoOfNewFlight();
        Flight newFlight = new Flight(info);
        admin.addFlight(newFlight);
    }
    public void removeFlight(){
        String flightNumber = view.getFlightNumber();
        admin.removeFlight(flightNumber);
    }
    public void updateSchedule(){
        String flightNumber = view.getFlightNumber() ,
               departureDate = view.getNewDepartureDate() ,
               arrivalDate = view.getNewArrivalDate();
        admin.updateSchedule(flightNumber, departureDate, arrivalDate);
    }
    public void expandCountOfSeats(){
        String flightNumber = view.getFlightNumber() ,
                newCountOfRows = view.newCountOfRows(),
                newFirstClassCols = view.getNewFirstClassCols(),
                newBusinessClassCols = view.getNewBusinessClassCols(),
                newEconomyClassCols = view.getNewEconomyClassCols();

        while(!admin.expandCountOfSeats(flightNumber , Integer.parseInt(newCountOfRows) , Integer.parseInt(newFirstClassCols) ,
               Integer.parseInt(newBusinessClassCols), Integer.parseInt(newEconomyClassCols))){
            System.out.println("");
            flightNumber = view.getFlightNumber() ;
            newCountOfRows = view.newCountOfRows();
            newFirstClassCols = view.getNewFirstClassCols();
            newBusinessClassCols = view.getNewBusinessClassCols();
            newEconomyClassCols = view.getNewEconomyClassCols();
        }

    }
}