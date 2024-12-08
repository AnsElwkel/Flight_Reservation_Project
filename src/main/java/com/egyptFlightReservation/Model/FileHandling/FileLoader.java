package com.egyptFlightReservation.Model.FileHandling;

import com.egyptFlightReservation.Model.Booking;
import com.egyptFlightReservation.Model.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileLoader {
    static void testFile(String fileName) {
        if (!new File(fileName).exists()) {
            System.out.println("File does not exist" + fileName);
        }
    }

    static void printFilesForTesting(String[] clientContent,String[] bookingHistory,
                                     String[] tickets, String[][] paymentMethods) {
        for (String client : clientContent) {
            System.out.print(client + " ");
        }
        System.out.println();
        for (int i = 0; i < bookingHistory.length; i++) {
            System.out.println(bookingHistory[i]);
//            System.out.println();
        }
        for (String ticket : tickets) {
            System.out.println(ticket);
        }
        System.out.println();
        for (String[] paymentMethod : paymentMethods) {
            for (String payment : paymentMethod) {
                System.out.print(payment + " ");
            }
            System.out.println();
        }
    }

    public static void loadUserTable() {
        try {
            String fileName = "/home/anaselwkel/IdeaProjects/Flight_Reservation_Project/src/Files/User/Client/ClientUsernames";
            File file = new File(fileName);

            testFile(fileName);

            Scanner fin = new Scanner(file);
            while (fin.hasNextLine()) {
                String userName = fin.nextLine();
//                System.out.println(userName); //testing

                String dir = "/home/anaselwkel/IdeaProjects/Flight_Reservation_Project/src/Files/User/Client/" + userName;

                //load info
                fileName = dir + "/info";

                testFile(fileName);
                String[] clientContent = loadFile(fileName)[0].split(" ");

                //load Booking
                fileName = dir + "/Booking";
                testFile(fileName);

                String[] bookingHistory = loadFile(fileName);


                //load Tickets
                fileName = dir + "/Ticket";
                testFile(fileName);

                String[] tickets = loadFile(fileName);

                //load payments
                testFile(dir + "/PaymentMethod/DebitCard");
                testFile(dir + "/PaymentMethod/MasterCard");
                testFile(dir + "/PaymentMethod/PayPal");
                testFile(dir + "/PaymentMethod/VodafoneCash");

                String[][] paymentMethods = {
                        loadFile(dir + "/PaymentMethod/DebitCard"),
                        loadFile(dir + "/PaymentMethod/MasterCard"),
                        loadFile(dir + "/PaymentMethod/PayPal"),
                        loadFile(dir + "/PaymentMethod/VodafoneCash")
                };

                printFilesForTesting(clientContent, bookingHistory, tickets, paymentMethods);
                Database.getDatabase().loadUserTable(userName ,clientContent, bookingHistory, tickets, paymentMethods);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }


    public static String[] loadFile(String fileName) {
        File file = new File(fileName);
        ArrayList<String> tmp = new ArrayList<>();
        try {
            Scanner fin = new Scanner(file);
            while (fin.hasNextLine())
                tmp.add(fin.nextLine());
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
