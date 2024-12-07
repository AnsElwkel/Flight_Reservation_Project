package com.egyptFlightReservation.Model.FileHandling;

import com.egyptFlightReservation.Model.Database;


public class FileAdministrator {
    FileSaver fileSaver;
    FileLoader fileLoader;
    Database database;
    public FileAdministrator() { //call this constructor at begin of app
        database = Database.getDatabase();
        fileSaver = FileSaver.getSaver();
//        fileLoader = FileLoader.getLoader();
        // function(s) to load all file content in containers
    }
}
