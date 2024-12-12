package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.userProfileView;

import java.util.Scanner;

public class ClientProfileController {
    private int choice;
    protected userProfileView userView;
    protected signUpController controller;
    protected Scanner scanner;
    private Client client ;
    public ClientProfileController() {
        this.userView = new userProfileView();
        this.controller = new signUpController();
        this.client = Database.getDatabase().getClient();

    }

    public void action(int choice) {
        switch (choice) {
            case 1:
                editUserInfo();
                break;
            case 2:
                displayBookingHistory();
                break;
            case 3:
                exitMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                mainMenu();
                break;
        }
    }

    public void message() {
        System.out.println("Updated Successfully.");
    }
    public void editUserInfo() {
        choice = userView.editUserInfoMenu(); //Show menu

        while ( !((1 <= choice && choice <= 4) || choice == -1) ) {
            System.out.print("Invalid Choice, Please Enter Your Choice (or -1 to go back): ");
            choice = userView.editUserInfoMenu();
        }

        switch (choice) {
            case 1:
                controller.validateUserName();
                message();
                editUserInfo();
                break;
            case 2:
                controller.validateEmail();
                message();
                editUserInfo();
                break;
            case 3:
                controller.validateMobile();
                message();
                editUserInfo();
                break;
            case 4:
                controller.isStrongPassword();///Nhndl eno yshof el pass el2deem///
                message();
                editUserInfo();
                break;
            case -1:
                mainMenu();
                break;
        }
    }
    public void displayBookingHistory(){
        String[] titles  = {"ID" , "Status" , "Booking Date","Airline","Flight Number" ,"Dep. Airport" ,"Arr. Airport",
                            "Dep. Date" , "Arr. Date" , "Seat Count" , "Total Price"};
        String[][] data = Database.getDatabase().getBookingHistory();
        userView.displayBookingHistory(titles , data);
    }
    public void exitMenu() {
        System.out.println("\nExiting the menu...");
    }
    public void mainMenu() {
        userView.displayUserProfile(client.getName() , client.getEmail() , client.getPhoneNumber());
        choice = userView.menu();
        while(!(1 <= choice && choice <= 3)) {
            System.out.println("Invalid Choice,Please Enter Your Choice:");
            choice = userView.menu();
        }
        action(choice);
    }
}
