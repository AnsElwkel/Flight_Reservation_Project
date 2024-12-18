package com.egyptFlightReservation.Model.User;

import com.egyptFlightReservation.Model.Booking;

import java.util.List;
import java.util.Scanner;

public class Client extends User {
    private static final int DEFAULT_PREMIUM_POINTS = 1000; // %10 discount
    private String passportNumber;
    private String gender;
    private String phoneNumber;
    private String birthDate;
    private int premiumPoints;

    public Client(String username, String name, String email, String password, String gender, String phoneNumber, String birthDate, String passportNumber , int premiumPoints) {
        super(username, name, email, password);
        this.passportNumber = passportNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.premiumPoints = (premiumPoints == -1 ? DEFAULT_PREMIUM_POINTS : premiumPoints);
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
    public void addPremiumPoints(int points){
        this.premiumPoints += points;
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
    public void subtractPremiumPoints(int points) {
        this.premiumPoints -= points;
    }
    @Override
    public String toString() {
        return super.toString() + " " + gender + " " +phoneNumber  + " " + birthDate + " " + passportNumber + " " + premiumPoints;
    }

    public int getPremiumPoints() {
        return premiumPoints;
    }
}