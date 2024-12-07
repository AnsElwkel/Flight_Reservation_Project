package com.egyptFlightReservation.Model;

import OurExceptions.RangeException;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.View.AdminView;
import com.egyptFlightReservation.View.FirstView;

import java.util.ArrayList;

public class AdminController {
    Admin admin;
    private AdminView view;

    public AdminController() {
        admin = new Admin();
        this.view = new AdminView();
    }

    public void process() throws RangeException {
        int choice = view.adminMenu();
        while (!((1 <= choice && choice <= 5) || choice == -1))
            choice = view.adminMenu();

        if(choice == 1) {
            ArrayList info = view.getInfoOfNewFlight();
            Flight newFlight = new Flight((String)info.get(0) , (String)info.get(1) , (String)info.get(2) ,
                    (String)info.get(3),(String)info.get(4),(int)info.get(5),(int)info.get(6));
            admin.addFlight((String)info.get(7) ,newFlight);

        }else if (choice == 2) {

        }else if (choice == 3) {

        }else if (choice == 4) {

        }else if (choice == 5) {
            //log out
            FirstView firstView = new FirstView();
            firstView.Run();
        }else {}//exit
    }
}
