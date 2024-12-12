package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;

import java.util.Scanner;

public class BookingView {
    public String check_confirmation(){
        System.out.println("\nConfirm booking? (yes/no):");
        return Input.cin.nextLine();
    }

    public void showBookingDetails(String[] titles , String[][] details){
        System.out.println("=========================");
        System.out.println("==== Booking Details ====");
        System.out.println("=========================");
        Tools.showTableFormat.show(titles , details);
        Menu.show(new String[]{"Pay" , "Cancel and back to home page"});
    }
    public int getChoice(){
        return MenuSelector.select("Enter Number" , 1 , 2);
    }

    public void showMassageOfFailedBooking(){
        System.out.println("==========================");
        System.out.println("==== Booking Failed ! ====");
        System.out.println("==========================");
    }

    public void showMassageOfSuccessfulBooking(){
        System.out.println("==============================");
        System.out.println("==== Booking Successful ! ====");
        System.out.println("==============================");
    }
}