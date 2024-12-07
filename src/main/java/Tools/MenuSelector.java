package Tools;
import OurExceptions.RangeException;

import java.util.Scanner;

public class MenuSelector {
    public static int select (String text , int from , int to) throws RangeException {
        int choice ;
        System.out.print(text + ": ");
        Scanner cin = new Scanner(System.in);
        choice = cin.nextInt();
        if(!(from <= choice && choice <= to || choice == -1))
            throw new RangeException(from , to);

        return choice;
    }
}
