package com.egyptFlightReservation.Model;


public class Ticket{

    private String TicketNumber, SeatNum , passengerName;
    private boolean TicketStatus;
    private double Fare ;


    public Ticket(String passengerName ,String TicketNumber,boolean TicketStatus,double Fare ,String SeatNum,
    String departureAirport,String arrivalAirport , String airlineName,String flightNumber){
        this.passengerName=passengerName;
        this.Fare=Fare;
        this.SeatNum=SeatNum;
        this.TicketNumber=TicketNumber;
        this.TicketStatus=TicketStatus;
    }

    public String get_ticket_number(){return TicketNumber;}
    public boolean get_ticketStatus(){return TicketStatus; }
    public double get_TicketFare(){return Fare;}
    public String get_seat_number(){return SeatNum;}
    public void display_details(){}

}

