package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;


public class ClientView {
    public int clientMenu(){
        Menu.show(new String[]{"View Profile", "Reserve Flight", "Log Out"});
        System.out.println("Enter your choice or -1 to exit");
        return Input.cin.nextInt();
    }
}
