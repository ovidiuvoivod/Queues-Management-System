package ro.utcluj.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public boolean validator(String input) throws Exception {
        if (input.equals("")) {
            throw new Exception("Incorrect input");
        }
        Pattern pattern = Pattern.compile("([0-9]+)|(\\D)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (matcher.group(2) != null) {
                throw new Exception("Invalid input");
            }
        }

        return true;
    }
}
