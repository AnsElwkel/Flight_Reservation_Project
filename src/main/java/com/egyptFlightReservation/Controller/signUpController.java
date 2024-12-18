package com.egyptFlightReservation.Controller;

import OurExceptions.RangeException;
import Tools.InputValidator;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.User.Client;
import com.egyptFlightReservation.View.FirstView;
import com.egyptFlightReservation.View.signUpView;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class signUpController {
    protected String userName, name, email, password,
            mobileNumber, date, gender, passportNumber;
    protected String confirmPassword;
    protected signUpView view;

    public signUpController() {
        this.view = new signUpView();
    }

    public boolean validateUserName() {
        this.userName = view.getUserName();
        while (!InputValidator.isValidUsername(userName)) {
            System.out.println((isContainSpaces(this.userName.trim()) ? "Please enter a username without spaces" :
                    "That username is already in use. Please try again."));
            this.userName = view.getUserName();
        }
        return true;
    }

    public void SignUpProcess() {
        validateInput();
        ClientController clientController = new ClientController();
        clientController.process();
    }


    public boolean isContainSpaces(String str) {
        return str.contains(" ");
    }

    public boolean validateEmail() {    //bashof lw elEmail a5ro @gmail.com
        this.email = view.getEmail();
        while (!InputValidator.isValidEmail(this.email)) {
            System.out.println("Email Not Valid,It must contain \"@gmail.com\" ");
            this.email = view.getEmail();
        }
        return true;
    }

    public boolean isStrongPassword() {
        this.password = view.getPassword();
        while (!InputValidator.isValidPassword(this.password)) {
            System.out.println("Invalid Password, try again");
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
        while (!InputValidator.isValidGender(this.gender)) {
            System.out.println("Gender it must be Female or Male only");
            this.gender = view.getGender();
        }
        return true;
    }


    public boolean validateMobile() {//Ahndl 01
        this.mobileNumber = view.getMobileNumber();
        while (!InputValidator.isValidPhoneNumber(mobileNumber)) {
            System.out.println("PhoneNumber must be 11 digit and must be started with 01");
            this.mobileNumber = view.getMobileNumber();

        }
        return true;
    }

    public boolean validatePassportNum() {

        this.passportNumber = view.getPassportNumber();
        while (!InputValidator.isValidPassport(passportNumber)) {
            System.out.println("PassportNumber must be 9 digit ");
            this.passportNumber = view.getPassportNumber();
        }
        return true;
    }

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

    public boolean validateName() {
        this.name = view.getFullName().trim().replaceAll(" ", "_"); // to make it easy in file handling
        return true;
    }

    public void validateInput() {
        if (validateUserName() && validateName() && validateEmail() && isStrongPassword() && confirmPassword() && validateGender()
                && validateMobile() && validatePassportNum() && validateAge()) {
            Database.getDatabase().setCurUser(userName);
            Database.getDatabase().addClient(new Client(userName, name, email, password, gender, mobileNumber, date, passportNumber , -1));
            System.out.println("SignUp Successfully");
        } else {
            System.out.println("SignUp failed ,Please check your inputs.");
            FirstView.Run();
        }
    }
}
