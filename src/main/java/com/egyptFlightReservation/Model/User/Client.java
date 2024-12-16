package com.egyptFlightReservation.Model.User;

import com.egyptFlightReservation.Model.Booking;

import java.util.List;
import java.util.Scanner;

public class Client extends User {
    private String passportNumber;
    private String gender;
    private String phoneNumber;
    private String birthDate;

    public Client(String username, String name, String email, String password, String gender, String phoneNumber, String birthDate, String passportNumber) {
        super(username, name, email, password);
        this.passportNumber = passportNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
    public Client(String[] info) {
        super(info[0], info[1], info[3], info[2]);
        this.birthDate = info[6];
        this.passportNumber = info[7];
        this.gender = info[5];
        this.phoneNumber = info[4];
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    String getGender(){
        return this.gender;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return super.toString() + " " + gender + " " +phoneNumber  + " " + birthDate + " " + passportNumber;
    }
}