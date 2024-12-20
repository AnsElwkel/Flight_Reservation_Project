package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.myPair;

import java.util.Scanner;


public class ClientView {
    public static Scanner cin = new Scanner(System.in);

    public int clientMenu(String username) {
        Scanner cin = new Scanner(System.in);
        System.out.println("Welcome " + username + " | UserView");
        Menu.show(new String[]{"View Profile", "Reserve Flight" , "Show Available Flights" ,"Rate the airline", "Log Out"});
        System.out.print("Enter number in range (1 - 5): ");
        return cin.nextInt();
    }
    public void showAirlines(String[] titles ,String[][] airlinesInfo){
        Tools.showTableFormat.show("All Airlines" , titles , airlinesInfo);
    }
    public int getChoiceToRateAirline(int len){
        System.out.print("Enter number in range (1 - " + len + "): ");
        return cin.nextInt();
    }
    public myPair<Integer,String> getTheRate(){
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter Rate (1 - 5): ");
        int rate = cin.nextInt();
        Scanner cin2 = new Scanner(System.in);
        System.out.println("Write the review: ");
        String review = cin2.nextLine();
        return new myPair<>(rate, review);
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
