package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import com.egyptFlightReservation.View.ClientView;
import com.egyptFlightReservation.View.FirstView;

public class ClientController {
    ClientView view;
    public ClientController(){
        view = new ClientView();
    }

    public void process() throws RangeException {
        int choice = view.clientMenu();
        if(choice == 1){

        }else if(choice == 2){

        }else if(choice == 3){
            FirstView firstView = new FirstView();
            firstView.Run();
        }else if(choice == -1){
            //exit()
        }

    }
}
