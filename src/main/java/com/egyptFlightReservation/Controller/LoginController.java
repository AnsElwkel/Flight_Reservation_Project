package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.LoginView;

public class LoginController {
    private String username , password;
    private LoginView loginView;
    private Client client;


    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }

    public boolean LoginProcess(){

        return false;
    }
    public void loginInput(){
        username = this.loginView.takeName();
        while(isContainSpaces(username.trim())){
            System.out.print("Enter a username without spaces: ");
            username = this.loginView.takeName();
        }
        password = this.loginView.takePassword();
        while(isContainSpaces(password.trim())){
            System.out.print("Enter a password without spaces: ");
            password = this.loginView.takePassword();
        }
    }
    public void validateUserName(){
        loginInput();

        if(Database.getDatabase().isAdmin(username , password)){
            //call adminController
        }else if(Database.getDatabase().isCorrectLogin(username, password)){
            Database.getDatabase().setCurUser(username);
        }else {
            System.out.println("Invalid username or password");
            validateUserName(); // recursion function
        }
    }

    public boolean isContainSpaces(String str){
        return str.contains(" ");
    }
}
