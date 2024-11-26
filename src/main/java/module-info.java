module com.example.flight_reservation_project {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.egyptFlightReservation;
    opens com.egyptFlightReservation to javafx.fxml;
}