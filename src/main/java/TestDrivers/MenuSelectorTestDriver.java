package TestDrivers;
import Tools.*;

public class MenuSelectorTestDriver {
    public static void main(String[] args) throws Exception {
        String[] arr = {"Ans" , "nn" , "aa"};
        Menu.show(arr);
        int x = MenuSelector.select("Select" , 1 , 3);
        System.out.println(x);
    }
}
