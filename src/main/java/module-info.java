module com.example.flight_reservation_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.smartcardio;


    exports com.egyptFlightReservation;
    opens com.egyptFlightReservation to javafx.fxml;
}