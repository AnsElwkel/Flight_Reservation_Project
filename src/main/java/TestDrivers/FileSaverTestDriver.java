package TestDrivers;

import com.egyptFlightReservation.Model.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaverTestDriver {
    public static void main(String[] args) throws IOException {
        String[] s = {"Anas" , "Hesham" , "Elwkel"};
        String fileName = "C:\\Users\\AnasElwkel\\IdeaProjects\\Flight Reservation Project\\src\\Main\\java\\TestDrivers\\Testing";
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileSaver.getSaver().save(fileName , s);
    }
}
