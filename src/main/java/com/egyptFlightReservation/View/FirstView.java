package com.egyptFlightReservation.View;

import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Controller.LoginController;
import com.egyptFlightReservation.Controller.signUpController;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.FileHandling.FileLoader;

public class FirstView {
    /// Implement some design and nice view :)


    public FirstView() {
        FileLoader.loadData();
//        FileLoader.loadAirports();
//        FileLoader.loadAdminTable();
//        FileLoader.loadAdminOperationsTable();

    }


    public static void Run() {
        System.out.println("Welcome to Egypt Flight Reservation System");
        Menu.show(new String[]{"Login", "Sign Up", "Exit"});
        int choice = MenuSelector.select("Enter Number", 1, 3);

        if (choice == 1) {
            LoginController loginController = new LoginController();
            loginController.LoginProcess();
        } else if (choice == 2) {
            System.out.println("--- Sign Up Page ---");
            signUpController signUp = new signUpController();
            signUp.SignUpProcess();
        } else {
            Database.getDatabase().saveData();
            System.exit(0);
        }
    }
}
