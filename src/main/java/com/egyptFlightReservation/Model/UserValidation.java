package com.egyptFlightReservation.Model;

public class UserValidation {
    private final String adminName = "AnasElwkel", adminPassword = "AnasElwkel_1234"; //mark them in container
    private String username, password;

    public UserValidation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int Validate() { //1 for admin , 2 for user , 0 not found
        if(username.equals(adminName) && password.equals(adminPassword))
            return 1;
        //check in container of app data if the info is found or not

        return 0;
    }

}
