package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.ClientView;
import com.egyptFlightReservation.View.FirstView;

public class ClientController {
    ClientView view;
    Client client ;
    public ClientController(){
        view = new ClientView();
        client = Database.getDatabase().getClient();
    }

    public void process() throws RangeException {
        int choice = view.clientMenu();
        while(choice > 3 || choice < 1){
            System.out.println("Invalid input");
            choice = view.clientMenu();
        }

        if(choice == 1){
            /// //////////
            showProfile();



        }else if(choice == 2){
            //reserve call call search engine

        }else if(choice == 3){
            FirstView.Run();
        }
    }


    public void showProfile(){
        //implement in this function
    }
}
