package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import Tools.Input;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.FirstView;
import com.egyptFlightReservation.View.LoginView;

import static Tools.Input.isContainSpaces;

public class LoginController {
    private String username , password;
    private LoginView loginView;
    private Client client;


    public LoginController(){
        this.loginView = new LoginView();
    }

    public void LoginProcess(){
        loginInput();
        validateLogin();
    }



    public void loginInput(){
        username = this.loginView.takeName();

        while(Input.isContainSpaces(username)){
            System.out.print("Please enter a username without spaces: ");
            username = this.loginView.takeName();
        }
        if(username.equals("-1")){
            FirstView.Run(); /// Return to first view
        }

        password = this.loginView.takePassword();
        while(Input.isContainSpaces(password)){
            System.out.print("Please enter a password without spaces:");
            password = this.loginView.takePassword();
        }
        if(password.equals("-1")){
            FirstView.Run();
        }
    }
    public void validateLogin() {
        if( username.equals(Admin.MAIN_ADMIN_NAME) && password.equals(Admin.MAIN_ADMIN_PASSWORD) ){/// MainAdmin
            AdminController adminController = new AdminController();
            adminController.MainAdminFunction();
        }else if(Database.getDatabase().isAdmin(username , password)){ /// Airline Admin
            //call adminController
            Database.getDatabase().setCurUser(username);
            AdminController adminController = new AdminController();
            adminController.AdminProcess();

        }else if(Database.getDatabase().isCorrectLogin(username, password)){ /// Client Admin
            Database.getDatabase().setCurUser(username);
            ClientController clientController = new ClientController();
            clientController.process();
        }else{
            System.out.println("Invalid username or password");
            loginInput(); //////// Recursion confusion possible
        }
    }


}
