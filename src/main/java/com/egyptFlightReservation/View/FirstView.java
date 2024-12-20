package com.egyptFlightReservation.View;

import OurExceptions.RangeException;
import Tools.Menu;
import Tools.MenuSelector;
import com.egyptFlightReservation.Controller.LoginController;
import com.egyptFlightReservation.Model.Client;

public class FirstView {
    public void Run() throws OurExceptions.RangeException {
        Menu.show(new String[]{"Login", "Sign Up"});
        int choice = MenuSelector.select("Select Number in Range (1 - 2)", 1, 2);

        if (choice == 1) {
            LoginController loginController = new LoginController(new LoginView());
            boolean isSuccess = loginController.LoginProcess();
            while (!isSuccess) {
                System.out.println("Invalid username or password ..try again");
                isSuccess = loginController.LoginProcess();
            }
            System.out.println("Successfully logged in (Admin)");
        }
    }
}
