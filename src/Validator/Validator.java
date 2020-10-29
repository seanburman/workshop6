package Validator;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isPresent(TextField tf, String fieldName, String message) {
        boolean isValid = true;
        if (tf.getText().isEmpty()) {

            createAlert(tf, fieldName, message);
            tf.setStyle("-fx-border-color: #ff0000");

            isValid = false;
        }
        return isValid;
    }

    public boolean isPresentNoAlert(TextField tf) {
        boolean isValid = true;
        if (tf.getText().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isNonNegativeDouble(TextField tf, String fieldName, String message ) {
        boolean isValid = true;

        try
        {
            double input = Double.parseDouble(tf.getText());
            if (input < 0)
            {
                createAlert(tf, fieldName, " must not be negative");
                isValid = false;
            }
        }
        catch (Exception e)
        {
            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }

    public boolean isNonNegativeInt(TextField tf, String fieldName, String message ) {
        boolean isValid = true;

        try
        {
            double input = Integer.parseInt(tf.getText());
            if (input < 0)
            {
                createAlert(tf, fieldName, " must not be negative");
                isValid = false;
            }
        }
        catch (Exception e)
        {
            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }

    //validate email
    public static boolean isValidEmail(TextField tf, String fieldName, String message) { //TextField tf, String fieldName, String message
        boolean isValid = true;

        String email = tf.getText();
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }
    //validate email
    public static boolean isValidEmailNoAlert(TextField tf) { //
        boolean isValid = true;

        String email = tf.getText();
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
//            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }


    //this matches phone format (403) 210-7801
    public static boolean isValidPhone(TextField tf, String fieldName, String message) { //
        boolean isValid = true;

        String phone = tf.getText();
        String regex = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
                //"^((\\(\\d{3}\\))|\\s{3})\\s\\d{3}[-]\\d{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }

    //this matches phone format (403) 210-7801
    public static boolean isValidPhoneNoAlert(TextField tf) { //TextField tf, String fieldName, String message
        boolean isValid = true;

        String phone = tf.getText();
        String regex = "^((\\(\\d{3}\\))|\\s{3})\\s\\d{3}[-]\\d{4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
//            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }
    //matches Canadian postal code in this format - T2E 0K6
    public static boolean isValidPostalCode(TextField tf, String fieldName, String message) { //
        boolean isValid = true;

        String postalCode = tf.getText();
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]\\s[0-9][A-Z][0-9]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        if (!matcher.matches()) {
           createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }

    //matches Canadian postal code in this format - T2E 0K6
    public static boolean isValidPostalCodeNoAlert(TextField tf) { //TextField tf, String fieldName, String message
        boolean isValid = true;

        String postalCode = tf.getText();
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]\\s[0-9][A-Z][0-9]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        if (!matcher.matches()) {
//            createAlert(tf, fieldName, message);
            isValid = false;
        }
        return isValid;
    }
    private static void createAlert(TextField tf, String fieldName, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(fieldName + " Input Error!");
        alert.setContentText(fieldName + message);
        alert.showAndWait();
        tf.isFocused();
    }
}
