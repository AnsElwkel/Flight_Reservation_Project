package com.egyptFlightReservation.Model.FileHandling;

import com.egyptFlightReservation.Model.Database;


public class FileAdministrator {
    public static final String ROOT_PATH = "/home/anaselwkel/IdeaProjects/Flight_Reservation_Project/src/Files/";
    FileSaver fileSaver;
    FileLoader fileLoader;
    Database database;

    public FileAdministrator() { //call this constructor at begin of app
        System.out.println("FileAdministrator initialized");
        database = Database.getDatabase();

        fileLoader.loadData();
//        fileLoader = FileLoader.getLoader();
        // function(s) to load all file content in containers
    }
}
