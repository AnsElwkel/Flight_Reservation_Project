package com.egyptFlightReservation;

import OurExceptions.RangeException;
import com.egyptFlightReservation.Model.FileLoader;
import com.egyptFlightReservation.Model.FileSaver;
import com.egyptFlightReservation.View.FirstView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException, RangeException {
        FirstView firstView = new FirstView();
        firstView.Run();
    }

}