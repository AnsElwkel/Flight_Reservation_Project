package com.egyptFlightReservation.View;

import Tools.Input;

import java.util.Scanner;

public class signUpView {
    public static Scanner cin = new Scanner(System.in);

    public signUpView() {
    }

    public String getFullName() {
        System.out.print("Full Name: ");
        return cin.nextLine();
    }

    public String getUserName() {
        System.out.println("User Name: ");
        return cin.nextLine();
    }

    public String getEmail() {
        System.out.print("Email: ");
        return cin.nextLine();
    }

    public String getPassword() {
        System.out.println("Enter Password (contain 8 character include digits, lower and upper case characters and special character lika(_,-, @)): ");
        return cin.nextLine();
    }

    public String getConfirmPassword() {
        System.out.print("Confirm Password: ");
        return cin.nextLine();
    }

    public String getMobileNumber() {
        System.out.print("Phone Number:");
        return cin.nextLine();
    }

    public String getDate() {
        System.out.println("birthdate in the format YYYY-MM-DD:");
        return cin.nextLine();
    }

    public String getGender() {
        System.out.print("Gender(Female/Male):");
        return cin.nextLine();
    }

    public String getPassportNumber() {
        System.out.print("Passport Number:");
        return cin.nextLine();
    }
}
