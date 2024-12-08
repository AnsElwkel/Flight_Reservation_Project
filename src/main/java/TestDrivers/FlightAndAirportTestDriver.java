package TestDrivers;

import com.egyptFlightReservation.Model.Airport;
import com.egyptFlightReservation.Model.Flight;

import java.util.Scanner;

public class FlightAndAirportTestDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Flight f1 = new Flight("2", "Cairo", "Dubai", "9 P.M", "12 P.M", 1500, 200);
        System.out.println(f1.getArrival_airport());
        Airport airport1 = new Airport("masr", "dwiha", "giza");
        System.out.println(airport1.getAirportcode());
    }
}
