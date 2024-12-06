package com.egyptFlightReservation.Model;


class Ticket{

    private int TicketNumber;
    private boolean TicketStatus;
    private double Fare ;
    private int SeatNum;

    public Ticket(int TicketNumber,boolean TicketStatus,double Fare ,int SeatNum){
        this.Fare=Fare;
        this.SeatNum=SeatNum;
        this.TicketNumber=TicketNumber;
        this.TicketStatus=TicketStatus;
    }
    public int get_ticket_number(){return TicketNumber;}
    public boolean get_ticketStatus(){return TicketStatus; }
    public double get_TicketFare(){return Fare;}
    public int get_seat_number(){return SeatNum;}
    public void display_details(){}

}

