package com.egyptFlightReservation.View;

import java.util.Scanner;

public class LoginView {
    private String username , password;
    private Scanner cin = new Scanner(System.in);
    LoginView() { }
    public String takeName(){
        System.out.println("Enter username : ");
        return cin.nextLine();
    }

    public String takePassword(){
        System.out.println("Enter password : ");
        return cin.nextLine();
    }
}
