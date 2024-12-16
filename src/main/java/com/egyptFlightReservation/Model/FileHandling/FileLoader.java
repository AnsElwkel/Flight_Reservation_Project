package com.egyptFlightReservation.Model.FileHandling;

import com.egyptFlightReservation.Model.Booking;
import com.egyptFlightReservation.Model.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class FileLoader {
    public static void loadData() {
        loadUserTable();
        loadAdminTable();
        loadAdminOperationsTable();
        loadAirports();
        System.out.println("Data loaded");
    }


    static boolean testFile(String fileName) {
        if (!new File(fileName).exists()) {
            System.out.println("File does not exist" + fileName);
            return false;
        }
        return true;
    }

    static void printFilesForTesting(String[] clientContent, String[] bookingHistory,
                                     String[] tickets, String[][] paymentMethods) {
        for (String client : clientContent) {
            System.out.print(client + " ");
        }
        System.out.println();
        for (int i = 0; i < bookingHistory.length; i++) {
            System.out.println(bookingHistory[i]);
//            System.out.println();
        }
//        for (String ticket : tickets) {
//            System.out.println(ticket);
//        }
//        System.out.println();
        if (paymentMethods != null && paymentMethods.length > 0) {
            for (String[] paymentMethod : paymentMethods) {
                if (paymentMethod == null) continue;
                for (String payment : paymentMethod) {
                    System.out.print(payment + " ");
                }
                System.out.println();
            }
        }
    }

    public static void loadUserTable() {
        try {
            String fileName = "/home/anaselwkel/IdeaProjects/Flight_Reservation_Project/src/Files/User/Client/ClientUsernames";
            File file = new File(FileAdministrator.ROOT_PATH + "User/Client/ClientUsernames");
            testFile(FileAdministrator.ROOT_PATH + "User/Client/ClientUsernames");

            Scanner fin = new Scanner(file);
            while (fin.hasNextLine()) {
                String userName = fin.nextLine();
//                System.out.println(userName); //testing

                String dir = FileAdministrator.ROOT_PATH +"User/Client/" + userName;

                //load info
                fileName = dir + "/info";

                testFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/info");
                String[] clientContent = loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/info")[0].split(" ");

                //load Booking
                fileName = dir + "/Booking";
                testFile(fileName);

                String[] bookingHistory = loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/Booking");


                //load Tickets
//                fileName = dir + "/Ticket";
//                testFile(fileName);
//
                String[] tickets = null;
//                tickets = loadFile(fileName);

                //load payments
                testFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/DebitCard");
                testFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/MasterCard");
                testFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/PayPal");
                testFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/VodafoneCash");

                String[][] paymentMethods = {
                        loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/DebitCard"),
                        loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/MasterCard"),
                        loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/PayPal"),
                        loadFile(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/VodafoneCash")
                };

                printFilesForTesting(clientContent, bookingHistory, tickets, paymentMethods);
                Database.getDatabase().loadUserTable(userName, clientContent, bookingHistory, tickets, paymentMethods);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void loadAdminTable() {
        testFile(FileAdministrator.ROOT_PATH + "User/Admin/Admins");
        String[] admins = loadFile(FileAdministrator.ROOT_PATH + "User/Admin/Admins");
        Database.getDatabase().loadAdminTable(admins);
    }

    public static void loadAdminOperationsTable() {

        testFile(FileAdministrator.ROOT_PATH + "Airline" + "/AirlineNames");
        String[] content = loadFile(FileAdministrator.ROOT_PATH + "Airline/" + "AirlineNames");

        for (String airlineName : content) {
            testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/info");
            String[] airlineInfo = loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/info");

            testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/flightNumber");
            String[] flightNumbers = loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/flightNumber");

            ArrayList<String[]> flightsInfo = new ArrayList<>();
            ArrayList<String[]> passengersInfo = new ArrayList<>(), ticketsInfo = new ArrayList<>(), seatsInfo = new ArrayList<>();
//            passengersInfo = ticketsInfo = seatsInfo = new ArrayList<>();
            if (flightNumbers != null && flightNumbers.length > 0) {
                for (String flightNumber : flightNumbers) {
                    if (testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/info")) {
                        flightsInfo.add(loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/info"));
                        System.out.println(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/info" + " is loaded !!!!!!!!!");
                    }


                    if (testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Passengers"))
                        passengersInfo.add(loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Passengers"));

                    if (testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Ticket"))
                        ticketsInfo.add(loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Ticket"));

                    if (testFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Seats"))
                        seatsInfo.add(loadFile(FileAdministrator.ROOT_PATH + "Airline/" + airlineName + "/Flight" + "/" + flightNumber + "/Seats"));
                }
            }

            Database.getDatabase().loadAdminOperations(airlineInfo, flightsInfo, passengersInfo, ticketsInfo, seatsInfo);
        }
    }

    public static void loadAirports() {
        testFile(FileAdministrator.ROOT_PATH + "Airports");
        String[] airports = loadFile(FileAdministrator.ROOT_PATH + "Airports");
        Database.getDatabase().loadAirports(airports);
    }

    public static String[] loadFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist" + fileName);
            return null;
        }
        ArrayList<String> tmp = new ArrayList<>();
        try {
            Scanner fin = new Scanner(file);
            while (fin.hasNextLine()) {
                String line = fin.nextLine();
                System.out.println("Line: " + line);
                tmp.add(line);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        String[] ret = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i++)
            ret[i] = tmp.get(i);

        return ret;
    }

    public static ArrayList<ArrayList<String>> loadSubsetsOfFile(String fileName) {
        ArrayList<ArrayList<String>> subsets = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner fin = new Scanner(file);
            ArrayList<String> subset = new ArrayList<>();
            while (fin.hasNextLine()) {
                String line = fin.nextLine();
                if (line.equals("")) {
                    subsets.add(subset);
                    subset = new ArrayList<>();
                } else {
                    subset.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return subsets;
    }
}
