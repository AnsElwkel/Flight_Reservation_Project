package com.egyptFlightReservation.Model;

class Passenger{
    private String Name;
    private  String ID;
    private  String PhoneNumber;
    private String  Email;


    public Passenger(String Name, String ID, String PhoneNumber, String Email){
        this.Name=Name;
        this.ID=ID;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
    }

    public String get_name(){return Name;}
    public String get_ID(){return ID;}
    public String get_phoneNumber(){return PhoneNumber;}
    public String get_Email(){return Email;}
    public void set_ticket(){};
    public void display_details(){}
}
