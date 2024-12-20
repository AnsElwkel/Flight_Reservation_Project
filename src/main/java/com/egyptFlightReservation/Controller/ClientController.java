package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.ClientView;
import com.egyptFlightReservation.View.FirstView;

public class ClientController {
    ClientView view;
    Client client;

    public ClientController() {
        view = new ClientView();
        client = Database.getDatabase().getClient();
    }

    public void process() {
        int choice = view.clientMenu(Database.getDatabase().getCurUser());
        while (choice > 4 || choice < 1) {
            System.out.println("Invalid input");
            choice = view.clientMenu(Database.getDatabase().getCurUser());
        }

        boolean again = false;
        if (choice == 1) {
            showProfile();
            again = true;
        } else if (choice == 2) {
            //reserve call search engine
            SearchingController searchingController = new SearchingController();
            if (!searchingController.searchProcess(1))
                process(); /// recursion checking !!!!!
            again = true;
        } else if (choice == 3) {
            SearchingController searchingController = new SearchingController();
            if (!searchingController.searchProcess(2))
                process(); /// recursion checking !!!!!
            again = true;
        }else if(choice == 4){
            FirstView.Run();
        }

        if (again) process();// recurse
    }


    public void showProfile() { /// Call profile controller
        ClientProfileController profileController = new ClientProfileController();
        profileController.mainMenu();
    }
}
