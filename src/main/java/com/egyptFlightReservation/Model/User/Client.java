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

    private List<Booking> bookings;


    public void viewBooking() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for " + getName() + ".");
        } else {
            System.out.println("Bookings for " + getName() + ":");
            for (Booking booking : bookings) {
                booking.displayBookingDetails();
                System.out.println("------------------------");
            }
        }
    }

    public void editInfo() {
        Scanner scanner = new Scanner(System.in);
        boolean keepEditing = true;

        while (keepEditing) {

            System.out.println("\nWhich information would you like to change?");
            System.out.println("1. Username");
            System.out.println("2. Name");
            System.out.println("3. Email");
            System.out.println("4. Password");
            System.out.println("5. Gender");
            System.out.println("6. Phone Number");
            System.out.println("7. Exit");
            System.out.print("Enter the number of the option you want to change: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.print("Enter new username (Current: " + username + "): ");
                    String newUsername = scanner.nextLine();
                    if (!newUsername.isEmpty()) {
                        this.username = newUsername;
                        System.out.println("Username updated successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter new name (Current: " + name + "): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        this.name = newName;
                        System.out.println("Name updated successfully!");
                    }
                    break;

                case 3:
                    System.out.print("Enter new email (Current: " + email + "): ");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.isEmpty()) {
                        this.email = newEmail;
                        System.out.println("Email updated successfully!");
                    }
                    break;

                case 4:
                    System.out.print("Enter new password (Current: " + password + "): ");
                    String newPassword = scanner.nextLine();
                    if (!newPassword.isEmpty()) {
                        this.password = newPassword;
                        System.out.println("Password updated successfully!");
                    }
                    break;

                case 5:
                    System.out.print("Enter new gender (Current: " + gender + "): ");
                    String newGender = scanner.nextLine();
                    if (!newGender.isEmpty()) {
                        this.gender = newGender;
                        System.out.println("Gender updated successfully!");
                    }
                    break;
                case 6:
                    System.out.print("Enter new phone number (Current: " + phoneNumber + "): ");
                    String newPhoneNumber = scanner.nextLine();
                    if (!newPhoneNumber.isEmpty()) {
                        this.phoneNumber = newPhoneNumber;
                        System.out.println("Phone number updated successfully!");
                    }
                    break;

                case 7:
                    System.out.println("Exiting the edit process.");
                    keepEditing = false;
                    break;

                default:
                    System.out.println("Invalid choice, please select a valid option.");
                    break;
            }


            if (keepEditing) {
                System.out.print("\nDo you want to change another information? (yes/no): ");
                String response = scanner.nextLine().toLowerCase();
                if (response.equals("no")) {
                    keepEditing = false;
                    System.out.println("Exiting the edit process.");
                }
            }
        }
    }
    public void cancelBooking() {
        if (bookings.isEmpty()) {
            System.out.println("You have no bookings to cancel.");
            return;
        }

        // Display  bookings
        System.out.println("Your current bookings:");
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            System.out.println((i + 1) + ". " + booking.toString());
        }

        // Ask the client to select the booking they want to cancel
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the booking you want to cancel: ");
        int bookingNumber = scanner.nextInt();
        scanner.nextLine();


        if (bookingNumber < 1 ||  bookingNumber > bookings.size()) {
            System.out.println("Invalid booking number. Please try again.");
            return;
        }

        Booking bookingToCancel = bookings.get(bookingNumber - 1);
        System.out.println("Are you sure you want to cancel the following booking?");
        System.out.println(bookingToCancel.toString());
        System.out.print("Enter 'yes' to confirm cancellation, 'no' to cancel: ");
        String confirmation = scanner.nextLine();

        if ("yes".equalsIgnoreCase(confirmation)) {
            bookings.remove(bookingToCancel);
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }



}