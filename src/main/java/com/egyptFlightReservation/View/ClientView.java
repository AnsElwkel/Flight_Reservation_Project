package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;


public class ClientView {
    public int clientMenu(){
        Menu.show(new String[]{"View Profile", "Reserve Flight", "Log Out"});
        System.out.println("Enter number in range (1 - 3)");
        return Input.cin.nextInt();
    }
}
