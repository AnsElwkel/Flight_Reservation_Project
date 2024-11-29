package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Client;
import com.egyptFlightReservation.Model.UserValidation;
import com.egyptFlightReservation.View.LoginView;
import com.egyptFlightReservation.View.UserView;
import javafx.util.Pair;

public class LoginController {
    LoginView loginView;
    UserValidation check;
    Client client;


    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }

    public boolean LoginProcess(){
        String username = this.loginView.takeName();
        String password = this.loginView.takePassword();

        check = new UserValidation(username , password);

        int isValid = check.Validate();
        if(isValid == 0)
            return false;
        else if(isValid == 2)
        //Open the Account
        client = new Client(username , password);
        return true;
    }
}
