package com.egyptFlightReservation.View;

import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Controller.LoginController;
import com.egyptFlightReservation.Controller.signUpController;

public class FirstView {
    public static void Run() throws OurExceptions.RangeException {
        Menu.show(new String[]{"Login", "Sign Up" , "Exit"});
        int choice = MenuSelector.select("Enter Number", 1, 3);

        if (choice == 1) {
            LoginController loginController = new LoginController(new LoginView());
            boolean isSuccess = loginController.LoginProcess();
            while (!isSuccess) {
                System.out.println("Invalid username or password ..try again");
                isSuccess = loginController.LoginProcess();
            }
        }else if (choice == 2) {
            signUpController signUp = new signUpController();
            signUp.SignUpProcess();
        }
    }
}
