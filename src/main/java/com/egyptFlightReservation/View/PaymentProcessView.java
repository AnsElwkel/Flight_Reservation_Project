package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;

import java.util.ArrayList;
import java.util.Scanner;

public class PaymentProcessView {
    public PaymentProcessView() {
    }

    public void showMenuOfMyPayments(String[] data) {
        System.out.println("=====================");
        System.out.println("==== My Payments ====");
        System.out.println("=====================");
        Menu.show(data);
    }

    public int getChoiceOfMyPayments(int size) {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter number in range (" + Math.min(size, 1) + " - " + size + ") or -1 to add new payment");
        return cin.nextInt();
    }

    public int showNewPaymentMenu() {
        Scanner cin = new Scanner(System.in);
        Menu.show(new String[]{"Master Card", "Debit Card", "PayPal", "Vodafone Cash", "Back to My Payments"});
        return MenuSelector.select("Enter Number", 1, 5);
    }

    public ArrayList<String> getCardDetails() {
        Scanner cin = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();
        System.out.println("Enter Holder Name:");
        data.add(cin.nextLine());
        System.out.println("Enter Card Number:");
        data.add(cin.nextLine());
        System.out.println("Enter Expiry Date (MM/YY):");
        data.add(cin.nextLine());
        System.out.println("Enter CVV:");
        data.add(cin.nextLine());
        return data;
    }

    public String getPayPalDetails() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Email:");
        return cin.nextLine();
    }

    public String getVodaPhoneCashDetails() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter Phone Number:");
        return cin.nextLine();
    }
}
