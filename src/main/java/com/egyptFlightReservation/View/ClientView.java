package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;

import java.util.Scanner;


public class ClientView {
    public static Scanner cin = new Scanner(System.in);

    public int clientMenu(String username) {
        System.out.println("Welcome " + username + " | UserView");
        Menu.show(new String[]{"View Profile", "Reserve Flight", "Log Out"});
        System.out.print("Enter number in range (1 - 3):");
        return cin.nextInt();
    }

    public ClientView() {
        firstMassage();
    }

    public void firstMassage() {
        System.out.println("====================================================");
        System.out.println("==== Welcome to Egypt Flight Reservation System ====");
        System.out.println("====================================================");
    }
}
