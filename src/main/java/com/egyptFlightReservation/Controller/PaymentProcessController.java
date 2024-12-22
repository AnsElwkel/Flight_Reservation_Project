package com.egyptFlightReservation.Controller;

import Tools.myPair;
import com.egyptFlightReservation.Model.Database;
import com.egyptFlightReservation.Model.Payment.*;
import com.egyptFlightReservation.View.PaymentProcessView;

import java.util.ArrayList;

public class PaymentProcessController {
    ArrayList<PaymentMethod> paymentMethods;
    int curPremiumPoints;
    boolean once;

    private PaymentProcessView view;

    public PaymentProcessController() {
        this.view = new PaymentProcessView();
        this.curPremiumPoints = Database.getDatabase().getUserPremiumPoints();
        this.once = false; /// To call the discount process only once
    }

    private ArrayList<PaymentMethod> myPaymentMethods() {
        return Database.getDatabase().getClientPayment();
    }

    public myPair<Boolean, Double> paymentProcess(double amount, int points) {
        myPair<Double ,Integer > discountProcessRet = new myPair<Double , Integer>();
        if(!once){
            discountProcessRet = discountProcess(amount); /// Discount Process using premium points
            amount = discountProcessRet.getFirst();
            points = discountProcessRet.getSecond();
            once = true;
        }

        this.paymentMethods = myPaymentMethods();
        String[] s = new String[paymentMethods.size()];
        for (int i = 0; i < paymentMethods.size(); i++)
            s[i] = paymentMethods.get(i).PublicPaymentDetails();
        view.showMenuOfMyPayments(s);
        int choice = view.getChoiceOfMyPayments(s.length , amount);

        if (choice == -1) {
            // add new Payment
            // call recursion
            addNewPayment();
            return paymentProcess(amount , points);
        } else {
            boolean isSuccess = myPaymentMethods().get(choice - 1).paymentProcess(amount);
            if(isSuccess){
                Database.getDatabase().subtractPremiumPoints(points);
                return new myPair<>(true , amount);
            }
            return new myPair<>(false , amount);
        }
    }
    public myPair<Double , Integer> discountProcess(double amount) {

        int choice = view.getChoiceOfDiscountInfo();
        while(!(choice == 1 || choice == -1)){
            System.out.println("Invalid choice , please try again");
            choice = view.getChoiceOfDiscountInfo();
        }

        if(choice == 1){
            int points = view.getDiscountPoint();
            while(!(0 <= points && points <= curPremiumPoints)){
                System.out.println("Invalid input , please try again");
                points = view.getDiscountPoint();
            }
            return new myPair<>(amount - ((double)1 * amount * (points / 10000.0)) , points);
        }
        return new myPair<>(amount , 0);
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
            newMethod = new PayPal(newPayPalEmail, PaymentMethod.DEFAULT_CASH);
        } else if (choice == 4) {
            String phoneNumber = view.getVodaPhoneCashDetails();
            newMethod = new VodafoneCash(phoneNumber, PaymentMethod.DEFAULT_CASH);
        }

        if (1 <= choice && choice <= 4) // save new method in database
            Database.getDatabase().addPaymentMethod(newMethod);
    }

}
