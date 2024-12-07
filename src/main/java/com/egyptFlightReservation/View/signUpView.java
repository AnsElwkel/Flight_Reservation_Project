package com.egyptFlightReservation.View;
import java.util.Scanner;

public class signUpView {

    protected Scanner scanner;

    public signUpView() {
        this.scanner = new Scanner(System.in);
    }

    public String getFirstName() {
        System.out.print("First Name:");
        return scanner.nextLine();
    }

    public String getLastName() {
        System.out.print("Mid Name:");
        return scanner.nextLine();
    }

    public String getMidName() {
        System.out.print("Last Name:");
        return scanner.nextLine();
    }

    public String getUserName() {
        System.out.print("User Name:");
        return scanner.nextLine();
    }

    public String getEmail() {
        System.out.print("Email:");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.print("Password:");
        return scanner.nextLine();
    }

    public String getConfirmPassword() {
        System.out.print("Confirm Password:");
        return scanner.nextLine();
    }

    public String getMobileNumber() {
        System.out.print("Phone Number:");
        return scanner.nextLine();
    }

    public String getDate() {
        System.out.println("birthdate in the format YYYY-MM-DD:");
        return scanner.nextLine();
    }

    public String getGender() {
        System.out.print("Gender(Female/Male):");
        return scanner.nextLine();
    }

    public String getPassportNumber() {
        System.out.print("Passport Number:");
        return scanner.nextLine();
    }
}
