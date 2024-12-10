package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;

import java.util.Scanner;

public class userProfileView {
    public userProfileView() { }

    public void displayUserProfile(String name, String email, String phoneNumber) {
        System.out.println("--- Your Profile ---");
        System.out.println("Username: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("---------------------------");

    }
    public int editUserInfoMenu() {
        System.out.println("--- Edit User Info ---");
        Menu.show(new String[]{"Name", "Email", "MobilePhone", "Password"});
        System.out.println("-1 - To Go Back To Menu");
        System.out.print("Enter your Choice: ");
        return Input.cin.nextInt();
    }

    public int menu() {
        System.out.println("Select an option:");
        Menu.show(new String[]{"Edit User Info", "View Booking Details", "Exit"});
        System.out.print("Enter your choice: ");
        return Input.cin.nextInt();
    }
    public void displayBookingHistory(String[] titles , String[][] data){
        Tools.showTableFormat.show(titles , data);
    }
}
