package com.egyptFlightReservation.Controller;

import Tools.myPair;
import com.egyptFlightReservation.Model.Airline;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.ClientView;
import com.egyptFlightReservation.View.FirstView;

import java.util.ArrayList;

public class ClientController {
    ClientView view;
    Client client;

    public ClientController() {
        view = new ClientView();
        client = Database.getDatabase().getClient();
    }

    public void process() {
        int choice = view.clientMenu(Database.getDatabase().getCurUser());
        while (choice > 5 || choice < 1) {
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
            rateTheAirline();
            again = true;
        }else if (choice == 5){
            FirstView.Run();
        }

        if (again) process();// recurse
    }
    public void rateTheAirline() {
        String[][] airlinesInfo = Database.getDatabase().getAirlines();
        String[] titles = {"Choice" , "Airline Name" , "Airline ID" , "Number Of Rating" , "Rating Average"};
        if(airlinesInfo != null && airlinesInfo.length > 0){
            view.showAirlines(titles, airlinesInfo);
            int choice = view.getChoiceToRateAirline(airlinesInfo.length);
            while(!(1 <= choice && choice <= airlinesInfo.length)){
                System.out.println("Invalid input , please try again");
                choice = view.getChoiceToRateAirline(airlinesInfo.length);
            }
            if(Database.getDatabase().isRatedBefore(airlinesInfo[choice-1][2])){
                Tools.Menu.showMessage("You have rated the airline before!.." , 1);
            }else{
                myPair<Integer , String> clientRate = view.getTheRate();
                Database.getDatabase().setRate(airlinesInfo[choice-1][2] , Math.min(5 , Math.max(0 , clientRate.getFirst())) , clientRate.getSecond());
                Tools.Menu.showMessage("Rating is done!.. " , 1);
            }
        }else {
            System.out.println("No airlines found");
        }

    }


    public void showProfile() { /// Call profile controller
        ClientProfileController profileController = new ClientProfileController();
        profileController.mainMenu();
    }
}
