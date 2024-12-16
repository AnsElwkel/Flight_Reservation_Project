package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Booking;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.View.BookingView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class BookingController {
    Booking booking;
    BookingView view;
    private String bookingID, airlineName, airlineID, flightNumber, departureAirport, arrivalAirport, countOfSeats;
    private LocalDate bookingDate, arrivalDate, departureDate;
    private int totalPrice;

    public BookingController(String airlineName, String flightNumber, String departureAirport,
                             LocalDate departureDate, String arrivalAirport,
                             LocalDate arrivalDate, String countOfSeats, int totalPrice) {
        this.airlineName = airlineName;
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
        this.arrivalAirport = arrivalAirport;
        this.arrivalDate = arrivalDate;
        this.countOfSeats = countOfSeats;
        this.view = new BookingView();
        this.bookingID = UUID.randomUUID().toString();
        this.bookingDate = LocalDate.now();
        this.totalPrice = totalPrice;

    }

    public boolean process() {
        String[] titles = {"Flight Number", "Airline Name", "Dep. Airport", "Depa. Date", "Arr. Airport", "Arr. Date", "No. of Seats", "Total Price"};
        String[][] details = {{this.flightNumber, this.airlineName, this.departureAirport, this.departureDate.toString(), this.arrivalAirport, this.arrivalDate.toString(), this.countOfSeats, String.valueOf(this.totalPrice)}};
        view.showBookingDetails(titles, details);
        int choice = view.getChoice();


        if (choice == 1) {
            //Handle payment process
            PaymentProcessController paymentProcessController = new PaymentProcessController();
            String[] info = {bookingID, bookingDate.toString(), Database.getDatabase().getCurUser(),
                    airlineName, flightNumber,
                    departureAirport, arrivalAirport,
                    departureDate.toString(), arrivalDate.toString(), countOfSeats,
                    String.valueOf(totalPrice)};
            ArrayList<String> bookingInfo = new ArrayList<String>();
            bookingInfo.addAll(java.util.Arrays.asList(info));
            for (String s : info) System.out.print(s + " ");
            System.out.println();
            if (paymentProcessController.paymentProcess(totalPrice)) {
                //make new tickets
                //flag the seats and add the passengers
                //save the booking
                bookingInfo.add("true");
                Database.getDatabase().addBooking(new Booking(bookingInfo));
                view.showMassageOfSuccessfulBooking();
                return true; /// return to seat selector to tell it save the change
            } else {
                //save the booking status as failed booking
                //return to home page
                bookingInfo.add("false");
                Database.getDatabase().addBooking(new Booking(bookingInfo));
                view.showMassageOfFailedBooking();
                return false;
            }

        }
        return false;

        /// return to home page

    }
}