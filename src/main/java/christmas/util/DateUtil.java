package christmas.util;

import christmas.exception.InvalidDateException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

import static christmas.constant.PlannerConsts.MONTH_OF_PLANNER;
import static christmas.constant.PlannerConsts.YEAR_OF_PLANNER;

public class DateUtil {

    private static final YearMonth yearMonth = YearMonth.of(YEAR_OF_PLANNER, MONTH_OF_PLANNER);

    public static LocalDate inputToLocalDate(String input) {
        int day = NumberUtil.parseDay(input);
        validateDay(day);

        try {
            return LocalDate.of(YEAR_OF_PLANNER, MONTH_OF_PLANNER, day);
        } catch (DateTimeException e) {
            throw new InvalidDateException();
        }
    }

    public static void validateDay(int day) {
        if (day < 1 || day > yearMonth.lengthOfMonth()) {
            throw new InvalidDateException();
        }
    }



}
