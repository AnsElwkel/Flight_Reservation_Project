package com.egyptFlightReservation.Controller;
import com.egyptFlightReservation.View.signUpView;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class signUpController {
    protected String userName, firstName, midName, lastName, email, password,
            mobileNumber, date, gender, passportNumber;
    protected String confirmPassword;
    protected Scanner scanner;
    protected signUpView view;

    public signUpController() {
        this.view = new signUpView();
    }

    public boolean validateUserName() {
        this.userName = view.getUserName();
        while (!(!isContainSpaces(this.userName.trim())
//                   && Database.getDatabase().isUniqueUserName(this.userName.trim()) // return true when the username is new
        )) {
            System.out.println((isContainSpaces(this.userName.trim()) ? "Please enter a username without spaces" :
                    "That username is already in use. Please try again.") + ": ");
            this.userName = view.getUserName();
        }
        return true;
    }

    public void SignUpProcess() {
        validateInput();

    }


    public boolean isContainSpaces(String str) {
        return str.contains(" ");
    }

    public boolean validateEmail() {    //bashof lw elEmail a5ro @gmail.com
        this.email = view.getEmail();
        while (!email.endsWith("gmail.com")) {
            System.out.println("Email Not Valid,It must contain \"@gmail.com\" ");
            this.email = view.getEmail();

        }
        return true;

    }

    public boolean isStrongPassword() {
        this.password = view.getPassword();
        while (!(password.matches(".*[0-9]{1,}.*") && password.matches(".*[-_@]{1,}.*") &&
                password.matches(".*[A-Z]{1,}.*") && password.matches(".*[a-z]{1,}.*") &&
                password.length() == 8)) {
            System.out.println("Enter Password (contain 8 character include digits, lower and upper case" +
                    " characters and special character lika(_,-, @)):");
            this.password = view.getPassword();
        }
        return true;
    }

    public boolean confirmPassword() {
        this.confirmPassword = view.getConfirmPassword();
        while (!password.equals(confirmPassword)) {
            System.out.println("ConfirmPassword it must match Password");
            this.confirmPassword = view.getConfirmPassword();

        }
        return true;
    }

    public boolean validateGender() {
        this.gender = view.getGender();
        while (!gender.equals("Female") && !gender.equals("Male")) {
            System.out.println("Gender it must be Female or Male only");
            this.gender = view.getGender();
        }
        return true;
    }


    public boolean validateMobile() {//Ahndl 01
        this.mobileNumber = view.getMobileNumber();
        while (!(String.valueOf(mobileNumber).length() == 11) || !String.valueOf(mobileNumber).startsWith("01")) {
            System.out.println("PhoneNumber must be 11 digit and must be started with 01");
            this.mobileNumber = view.getMobileNumber();

        }
        return true;
    }

    public boolean validatePassportNum() {
        this.passportNumber = view.getPassportNumber();
        while (passportNumber.length() != 9) {
            System.out.println("PassportNumber must be 9 digit ");
            this.passportNumber = view.getPassportNumber();
        }
        return true;
    }

//    public int calculateAge() {
//        LocalDate birthDateParsed = LocalDate.parse(date);
//        LocalDate currentDate = LocalDate.now();
//        Period age = Period.between(birthDateParsed, currentDate);
//        return age.getYears();
//    }

    public int calculateAge() {
        try {
            LocalDate birthDateParsed = LocalDate.parse(date);//hna b7wl l format eldate
            LocalDate currentDate = LocalDate.now();//hna bashof date elnahrda
            Period age = Period.between(birthDateParsed, currentDate);//b7sb el differance
            return age.getYears();
        } catch (DateTimeParseException e) {//hna 2shan byb2a fe exception lw elformat 8lt
            return -1;
        }
    }

    public boolean validateAge() {
        while (true) {
            this.date = view.getDate();
            int age = calculateAge();

            if (age == -1) { //le el format 8lt
                System.out.println("Error: The date format is incorrect. Please enter a valid date (YYYY-MM-DD).");
            } else if (age >= 16) {
                return true;
            } else
                System.out.println("Your age must be 16 or older to SignUp.");

        }
    }

    public void validateInput() {
        System.out.println(((validateUserName() && validateEmail() && isStrongPassword() && confirmPassword() && validateGender()
                && validateMobile() && validatePassportNum() && validateAge() ? "SignUp Successfully." : "SignUp failed ,Please check your inputs.")));
    }
}
