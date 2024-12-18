package Tools;

import OurExceptions.RangeException;

import java.util.Scanner;

public class MenuSelector {
    static Scanner cin = new Scanner(System.in);

    public static int select(String text, int from, int to) {
        int choice;
        System.out.print(text + " (range " + from + " - " + to + ") : ");

        choice = cin.nextInt();
        while ((!(from <= choice && choice <= to))) {
            System.out.println("Invalid choice. Try again.");
            choice = cin.nextInt();
        }
        return choice;
    }
}
