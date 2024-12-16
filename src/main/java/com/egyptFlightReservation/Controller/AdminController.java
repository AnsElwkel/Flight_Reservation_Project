package com.egyptFlightReservation.Controller;

import Tools.Input;
import com.egyptFlightReservation.Model.Airport;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Flight;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.View.AdminView;
import com.egyptFlightReservation.View.FirstView;

import java.util.ArrayList;

/// add show all airports , show all admins , show all clients
public class AdminController {
    Admin admin;
    private AdminView view;

    public AdminController() {
        this.view = new AdminView();
    }

    public void MainAdminFunction() {
        int choice;
        choice = view.mainAdminMenu();
        if (choice == 1)
            addNewAdminProcess();
        else if (choice == 2)
            addNewAirport();
        else
            FirstView.Run();
    }

    public void addNewAirport() {
        ArrayList<String> info = view.getInfoOfNewAirport();
        Airport airport = new Airport(info.get(0), info.get(1), info.get(2));
        Database.getDatabase().addNewAirport(airport);
        MainAdminFunction(); //rec
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

        Admin newAdmin = new Admin(adminUserName, adminName, adminEmail, adminPassword, airlineName);
        Database.getDatabase().createNewAdmin(adminUserName, newAdmin, airlineName, airlineCode, airlineLocation);
        MainAdminFunction(); //rec
    }

    public void AdminProcess() {
        admin = Database.getDatabase().getAdmin();

        int choice;
        do {
            choice = view.adminMenu();
            while (!((1 <= choice && choice <= 6))) {
                System.out.println("Invalid Number");
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

        } while (choice != 5);
        //log out
        FirstView.Run();
    }

    public void addFlight() {
        ArrayList<String> info = view.getInfoOfNewFlight();
        if (Database.getDatabase().getAirline() == null) System.out.println("Airline not found");
        info.add(Database.getDatabase().getAirline().get_name());
        Flight newFlight = new Flight(info);
        admin.addFlight(newFlight);
    }

    public void removeFlight() {
        String flightNumber = view.getFlightNumber();
        admin.removeFlight(flightNumber);
    }

    public void updateSchedule() {
        String flightNumber = view.getFlightNumber(),
                departureDate = view.getNewDepartureDate(),
                arrivalDate = view.getNewArrivalDate();
        admin.updateSchedule(flightNumber, departureDate, arrivalDate);
    }

    public void expandCountOfSeats() {
        String flightNumber = view.getFlightNumber(),
                newCountOfRows = view.newCountOfRows(),
                newFirstClassCols = view.getNewFirstClassCols(),
                newBusinessClassCols = view.getNewBusinessClassCols(),
                newEconomyClassCols = view.getNewEconomyClassCols();

        while (!admin.expandCountOfSeats(flightNumber, Integer.parseInt(newCountOfRows), Integer.parseInt(newFirstClassCols),
                Integer.parseInt(newBusinessClassCols), Integer.parseInt(newEconomyClassCols))) {
            System.out.println("");
            flightNumber = view.getFlightNumber();
            newCountOfRows = view.newCountOfRows();
            newFirstClassCols = view.getNewFirstClassCols();
            newBusinessClassCols = view.getNewBusinessClassCols();
            newEconomyClassCols = view.getNewEconomyClassCols();
        }

    }
}