package com.egyptFlightReservation.View;

import Tools.Input;

import java.util.Scanner;

public class LoginView {
    public LoginView() {
        System.out.println("--- Login Page ---");
    }
    public String takeName(){
        String name;
        System.out.println("Enter username (or -1 to back to previous page): ");
        System.out.println();
        return Input.cin.nextLine();
    }

    public String takePassword(){
        System.out.println("Enter password (or -1 to back to previous page): ");
        return Input.cin.nextLine();
    }
}
