package com.egyptFlightReservation.Controller;

import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Payment.*;
import com.egyptFlightReservation.View.PaymentProcessView;

import java.util.ArrayList;

public class PaymentProcessController {
    ArrayList<PaymentMethod> paymentMethods;

    private PaymentProcessView view;

    public PaymentProcessController() {
        this.view = new PaymentProcessView();
    }

    private ArrayList<PaymentMethod> myPaymentMethods() {
        return Database.getDatabase().getClientPayment();
    }

    public boolean paymentProcess(int amount) {
        this.paymentMethods = myPaymentMethods();
        String[] s = new String[paymentMethods.size()];
        for (int i = 0; i < paymentMethods.size(); i++)
            s[i] = paymentMethods.get(i).PublicPaymentDetails();
        view.showMenuOfMyPayments(s);
        int choice = view.getChoiceOfMyPayments(s.length);

        if (choice == -1) {
            // add new Payment
            // call recursion
            addNewPayment();
            return paymentProcess(amount);
        } else {
            return myPaymentMethods().get(choice - 1).paymentProcess(amount);
        }
    }

    public void addNewPayment() {
        int choice = view.showNewPaymentMenu();
        PaymentMethod newMethod = null;
        ArrayList<String> info = null;
        if (choice == 1) {
            info = view.getCardDetails();
            newMethod = new MasterCard(info);
        } else if (choice == 2) {
            info = view.getCardDetails();
            newMethod = new DebitCard(info);
        } else if (choice == 3) {
            String newPayPalEmail = view.getPayPalDetails();
            newMethod = new PayPal(newPayPalEmail, 20000);
        } else if (choice == 4) {
            String phoneNumber = view.getVodaPhoneCashDetails();
            newMethod = new VodafoneCash(phoneNumber, 20000);
        }

        if (1 <= choice && choice <= 4) // save new method in database
            Database.getDatabase().addPaymentMethod(newMethod);

        if (choice == 5) {
            return;
        } else {
            System.out.println("Invalid choice....Something is wrong in payment process controller");
        }
    }

}
