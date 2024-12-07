package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Seat.Seat;
import com.egyptFlightReservation.View.SeatSelectorView;

import java.util.ArrayList;

public class SeatSelectorController {
    public SeatSelectorView view;
    ArrayList<ArrayList<Seat>> seats;
    public SeatSelectorController(ArrayList<ArrayList<Seat>> seats){
        this.seats = seats;
        view = new SeatSelectorView();
    }

    void selectionProcess(){
        view.showMap(seats);



    }
}
