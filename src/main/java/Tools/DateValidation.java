package Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateValidation {
    public static DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LocalDate getDate(String msg) {
        System.out.print(msg + "in format (yyyy-MM-dd): ");
        String s = Input.cin.nextLine();
        try{
            LocalDate ret = LocalDate.parse(s , dateFormatter);
            return ret;
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
