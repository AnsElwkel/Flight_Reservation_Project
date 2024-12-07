package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;

import java.util.ArrayList;

public class AdminView {
    public int adminMenu() {
        Menu.show(new String[]{"Add Flight", "Remove Flight", "Update Schedule",
                "Update count of seats", "Log out"});
        return Input.cin.nextInt();
    }

    public ArrayList getInfoOfNewFlight() {
        String flight_number, departure_airport ,arrival_airport , departureTime, arrivalTime , airlineName;
        int totalSeats;
        int standardPrice;
        System.out.println("Enter airline Name:");
        airlineName = Input.cin.nextLine();
        System.out.print("Enter Flight ID: ");
        flight_number = Input.cin.nextLine();
        System.out.print("Enter Departure Airport Name: ");
        departure_airport = Input.cin.nextLine();
        System.out.print("Enter Arrival Airport Name: ");
        arrival_airport = Input.cin.nextLine();
        System.out.print("Enter Departure Time: ");
        departureTime = Input.cin.nextLine();
        System.out.print("Enter Arrival Time: ");
        arrivalTime = Input.cin.nextLine();
        System.out.print("Enter Total Seats: ");
        totalSeats = Input.cin.nextInt();
        System.out.print("Enter Standard Price: ");
        standardPrice = Input.cin.nextInt();

        ArrayList info = new ArrayList();
        info.add(flight_number);
        info.add(departure_airport);
        info.add(arrival_airport);
        info.add(departureTime);
        info.add(arrivalTime);
        info.add(totalSeats);
        info.add(standardPrice);
        info.add(airlineName);

        return info;
    }

}
