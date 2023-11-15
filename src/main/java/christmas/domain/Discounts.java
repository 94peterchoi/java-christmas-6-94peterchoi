package christmas.domain;

import christmas.domain.discount.Discount;
import christmas.domain.discount.context.DiscountContext;
import christmas.domain.discount.impl.DDayDiscount;
import christmas.domain.discount.impl.SpecialDiscount;
import christmas.domain.discount.impl.WeekdayDiscount;
import christmas.domain.discount.impl.WeekendDiscount;
import christmas.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Discounts {

    private static final int MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT = 10000;

    private final List<Discount> discounts;

    public Discounts(DiscountContext context) {
        if (context.getOrders().getTotalOrderAmountBeforeDiscounts() >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT) {
            this.discounts = generatePossibleDiscounts(context);
        } else {
            this.discounts = new ArrayList<>();
        }
    }

    private List<Discount> generatePossibleDiscounts(DiscountContext context) {
        List<Discount> possibleDiscounts = new ArrayList<>();
        LocalDate reservationDate = context.getReservationDate();

        addWeekendOrWeekdayDiscount(possibleDiscounts, reservationDate, context);
        addWeekdayDiscount(possibleDiscounts, reservationDate, context);
        addSpecialDayDiscount(possibleDiscounts, reservationDate, context);
        addDDayDiscount(possibleDiscounts, reservationDate, context);

        return possibleDiscounts;
    }

    private void addWeekendOrWeekdayDiscount(List<Discount> discounts, LocalDate date, DiscountContext context) {
        if (DateUtil.isWeekend(date)) {
            addDiscount(discounts, new WeekendDiscount(context));
        }
    }

    private void addWeekdayDiscount(List<Discount> discounts, LocalDate date, DiscountContext context) {
        if (!DateUtil.isWeekend(date)) {
            addDiscount(discounts, new WeekdayDiscount(context));
        }
    }

    private void addSpecialDayDiscount(List<Discount> discounts, LocalDate date, DiscountContext context) {
        if (DateUtil.isSpecialDay(date)) {
            addDiscount(discounts, new SpecialDiscount(context));
        }
    }

    private void addDDayDiscount(List<Discount> discounts, LocalDate date, DiscountContext context) {
        if (DateUtil.isDDayLeft(date)) {
            addDiscount(discounts, new DDayDiscount(context));
        }
    }

    private void addDiscount(List<Discount> discounts, Discount discount) {
        if (discount.getCalculatedDiscount() > 0) {
            discounts.add(discount);
        }
    }

    public int getTotalDiscountAmount() {
        return discounts.stream()
                .mapToInt(Discount::getCalculatedDiscount)
                .sum();
    }

}
