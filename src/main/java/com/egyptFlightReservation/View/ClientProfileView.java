package com.egyptFlightReservation.View;

import Tools.Menu;

import java.util.Scanner;

public class ClientProfileView {
    public ClientProfileView() {
    }

    public void displayUserProfile(String username, String name, String email, String phoneNumber) {
        System.out.println("------- Your Profile -------");
        System.out.println("Username: " + username);
        System.out.println("Name: " + name.replaceAll("_", " "));
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("---------------------------");
    }

    public int editUserInfoMenu() {
        Scanner cin = new Scanner(System.in);
        System.out.println("--- Edit User Info ---");
        Menu.show(new String[]{"Username", "Name", "Email", "MobilePhone", "Password"});
        System.out.println("-1 - To Go Back To Menu");
        System.out.print("Enter your Choice: ");
        return cin.nextInt();
    }

    public int menu() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Select an option:");
        Menu.show(new String[]{"Edit User Info", "View Booking Details", "Back To Home Page"});
        System.out.print("Enter your choice: ");
        return cin.nextInt();
    }

    public void displayBookingHistory(String[] titles, String[][] data) {
        if (data.length == 0) {
            System.out.println("No Booking History Found");
            return;
        }

        Tools.showTableFormat.show("Booking Histroy", titles, data);
    }

    public String getNewUsername() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter new username: ");
        return cin.nextLine();
    }

    public String getNewEmail() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter new email: ");
        return cin.nextLine();
    }

    public String getNewPhoneNumber() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter new phone number: ");
        return cin.nextLine();
    }

    public String getNewUserPassword() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter new password (contain 8 character include digits, lower and upper case characters and special character lika(_,-, @)): ");
        return cin.nextLine();
    }

    public String getNewUserConfirmPassword() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Confirm the password: ");
        return cin.nextLine();
    }

    public String getNewFullName() {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter new full name: ");
        return cin.nextLine();
    }


}
