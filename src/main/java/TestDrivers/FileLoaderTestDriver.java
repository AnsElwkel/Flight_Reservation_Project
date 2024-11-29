package TestDrivers;

import com.egyptFlightReservation.Model.FileLoader;

import java.util.ArrayList;

public class FileLoaderTestDriver {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\AnasElwkel\\IdeaProjects\\Flight Reservation Project\\src\\Files\\Admin";
        ArrayList<String> arr = FileLoader.getLoader().load(fileName);

        System.out.println(arr.size());

        for (String ss : arr)
            System.out.println(ss);


    }
}
