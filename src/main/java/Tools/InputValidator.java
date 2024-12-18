package Tools;

import com.egyptFlightReservation.Model.Database;

public class InputValidator {
    public static boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com");
    }

    public static boolean isValidPassword(String password) {
        return (password.matches(".*[0-9]{1,}.*") && password.matches(".*[-_@]{1,}.*") &&
                password.matches(".*[A-Z]{1,}.*") && password.matches(".*[a-z]{1,}.*") &&
                password.length() == 8);
    }

    public static boolean isValidPhoneNumber(String mobileNumber) {
        for(char c : mobileNumber.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return String.valueOf(mobileNumber).length() == 11 && String.valueOf(mobileNumber).startsWith("01");
    }

    public static boolean isValidGender(String gender) {
        return gender.toLowerCase().equals("female") || gender.toLowerCase().equals("male");
    }

    public static boolean isValidPassport(String passport) {
        for (char c : passport.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return passport.length() == 9;
    }

    public static boolean isValidUsername(String username) {
        return (!Input.isContainSpaces(username.trim()) && username.length() >= 3
                && Database.getDatabase().isUniqueUserName(username.trim()));
    }

    public static String replaceSpaces(String input) {
        return input.replaceAll(" ", "_");
    }

}
