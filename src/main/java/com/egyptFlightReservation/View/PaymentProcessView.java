package com.egyptFlightReservation.View;

import Tools.Input;
import Tools.Menu;
import Tools.MenuSelector;

import java.util.ArrayList;

public class PaymentProcessView {
    public PaymentProcessView() {}

    public void showMenuOfMyPayments(String [] data){
        System.out.println("=====================");
        System.out.println("==== My Payments ====");
        System.out.println("=====================");
        Menu.show(data);
    }
    public int getChoiceOfMyPayments(int size){
        System.out.println("Enter number in range (1 -" + size + ") or -1 to add new payment");
        return Input.cin.nextInt();
    }
    public int showNewPaymentMenu(){
        Menu.show(new String[]{"Master Card" , "Debit Card" , "PayPal" , "Vodafone Cash" , "Back to My Payments"});
        return MenuSelector.select("Enter Number" , 1 , 5);
    }
    public ArrayList<String> getCardDetails(){
        ArrayList<String> data = new ArrayList<>();
        System.out.println("Enter Holder Name:");
        data.add(Input.cin.nextLine());
        System.out.println("Enter Card Number:");
        data.add(Input.cin.nextLine());
        System.out.println("Enter Expiry Date (MM/YY):");
        data.add(Input.cin.nextLine());
        System.out.println("Enter CVV:");
        data.add(Input.cin.nextLine());
        return data;
    }
    public String getPayPalDetails(){
        ArrayList<String> data = new ArrayList<>();
        System.out.println("Enter Email:");
        return Input.cin.nextLine();
    }
    public String getVodaPhoneCashDetails(){
        System.out.println("Enter Phone Number:");
        return Input.cin.nextLine();
    }
}
