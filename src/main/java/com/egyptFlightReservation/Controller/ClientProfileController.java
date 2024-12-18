package com.egyptFlightReservation.Controller;

import Tools.Input;
import Tools.InputValidator;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.ClientProfileView;

import java.util.Scanner;

public class ClientProfileController {
    private int choice;
    protected ClientProfileView view;
    protected Scanner scanner;
    private Client client;

    public ClientProfileController() {
        this.view = new ClientProfileView();
        this.client = Database.getDatabase().getClient();
    }

    public void action(int choice) {
        if (choice == 1)
            editUserInfo();
        else if (choice == 2) {
            displayBookingHistory();
            mainMenu();
        } else if (choice == 3)
            BackToHomePage();
    }

    public void message() {
        System.out.println("Updated Successfully.");
    }

    public void editUserInfo() {
        choice = view.editUserInfoMenu(); //Show menu

        while (!((1 <= choice && choice <= 5) || choice == -1)) {
            System.out.print("Invalid Choice, Please Enter Your Choice (or -1 to go back): ");
            choice = view.editUserInfoMenu();
        }

        if (choice == 1)
            editUsername();
        else if (choice == 2)
            editFullName();
        else if (choice == 3)
            editEmail();
        else if (choice == 4)
            editPhoneNumber();
        else if (choice == 5)
            editPassword();

        if (1 <= choice && choice <= 5)
            message();

        mainMenu();
    }

    public void displayBookingHistory() {
        String[] titles = {"ID", "Status", "Booking Date", "Airline", "Flight Number", "Dep. Airport", "Arr. Airport",
                "Dep. Date", "Arr. Date", "Seat Count", "Total Price"};
        String[][] data = Database.getDatabase().getBookingHistory();
        view.displayBookingHistory(titles, data);
    }

    public void mainMenu() {
        view.displayUserProfile(client.getUsername(), client.getName(), client.getEmail(), client.getPhoneNumber());
        choice = view.menu();
        while (!(1 <= choice && choice <= 3)) {
            System.out.println("Invalid Choice,Please Enter Your Choice:");
            choice = view.menu();
        }
        action(choice);
    }

    public void BackToHomePage() {
        ClientController clientController = new ClientController();
        clientController.process();
    }

    public void editUsername() {
        String newUsername = view.getNewUsername();
        while (!InputValidator.isValidUsername(newUsername)) {
            System.out.print("Invalid username" + (Input.isContainSpaces(newUsername) ? "Should not contain spaces" : "Username already exists") + " , try again");
            newUsername = view.getNewUsername();
        }
        Database.getDatabase().editUsername(newUsername);
    }

    public void editEmail() {
        String newEmail = view.getNewEmail();
        while (!InputValidator.isValidEmail(newEmail)) {
            System.out.println("Invalid email , should end with \"@gmail.com\" try again");
            newEmail = view.getNewEmail();
        }
        Database.getDatabase().editEmail(newEmail);
    }

    public void editPhoneNumber() {
        String newPhoneNumber = view.getNewPhoneNumber();
        while (!InputValidator.isValidPhoneNumber(newPhoneNumber)) {
            System.out.println("Invalid phone number, try again");
            newPhoneNumber = view.getNewPhoneNumber();
        }
        Database.getDatabase().editPhoneNumber(newPhoneNumber);
    }

    public void editFullName() {
        String newFullName = view.getNewFullName().trim().replaceAll(" ", "_");
        Database.getDatabase().editFullName(newFullName);
    }

    public void editPassword() {
        String newPassword = view.getNewUserPassword();
        while (!InputValidator.isValidPassword(newPassword)) {
            System.out.println("Invalid password, try again");
            newPassword = view.getNewUserPassword();
        }
        Database.getDatabase().editPassword(newPassword);
    }
}