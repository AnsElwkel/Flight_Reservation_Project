package com.egyptFlightReservation.View;

import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Controller.LoginController;
import com.egyptFlightReservation.Controller.signUpController;

public class FirstView {
    /// Implement some design and nice view :)






    public static void Run()  {
        System.out.println("Welcome to Egypt Flight Reservation System");
        Menu.show(new String[]{"Login", "Sign Up" , "Exit"});
        int choice = MenuSelector.select("Enter Number", 1, 3);

        if (choice == 1) {
            LoginController loginController = new LoginController();
            loginController.LoginProcess();
        }else if (choice == 2) {
            signUpController signUp = new signUpController();
            signUp.SignUpProcess();
        }
    }
}
