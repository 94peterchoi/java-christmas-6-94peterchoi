package christmas.util;

import java.text.DecimalFormat;

import static christmas.constant.PlannerConsts.MONEY_UNIT;

public class StringUtil {
    public static String formatCurrency(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedAmount = decimalFormat.format(amount);
        return formattedAmount + MONEY_UNIT;
    }
}
