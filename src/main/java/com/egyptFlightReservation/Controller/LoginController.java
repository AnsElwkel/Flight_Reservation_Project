package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import Tools.Input;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.LoginView;

import static Tools.Input.isContainSpaces;

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
        while(Input.isContainSpaces(username)){
            System.out.print("Enter a username without spaces: ");
            username = this.loginView.takeName();
        }
        password = this.loginView.takePassword();
        while(Input.isContainSpaces(password)){
            System.out.print("Enter a password without spaces: ");
            password = this.loginView.takePassword();
        }
    }
    public void validateUserName() throws RangeException {
        loginInput();

        if( username.equals(Admin.MAIN_ADMIN_NAME) && password.equals(Admin.MAIN_ADMIN_PASSWORD) ){
            AdminController adminController = new AdminController();
            adminController.MainAdminFunction();
        }else if(Database.getDatabase().isAdmin(username , password)){
            //call adminController
            Database.getDatabase().setCurUser(username);
            AdminController adminController = new AdminController();
            adminController.AdminProcess();

        }else if(Database.getDatabase().isCorrectLogin(username, password)){
            Database.getDatabase().setCurUser(username);
        }else {
            System.out.println("Invalid username or password");
            validateUserName(); // recursion function
        }
    }


}
