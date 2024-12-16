package com.egyptFlightReservation;

import OurExceptions.RangeException;
import com.egyptFlightReservation.View.FirstView;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException, RangeException {
        FirstView firstView = new FirstView();
        firstView.Run();
    }
}