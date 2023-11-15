package christmas.util;

import christmas.exception.InvalidDateException;

import java.util.regex.Pattern;

public class NumberUtil {

    public static int parseDay(String input) {
        String regex = "([1-9]|[12][0-9]|3[0-1])"; // 1 ~ 31

        if (Pattern.matches(regex, input)) {
            return Integer.parseInt(input);
        } else {
            throw new InvalidDateException();
        }
    }

}
