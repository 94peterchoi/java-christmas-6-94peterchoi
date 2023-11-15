package christmas.domain.discount.impl;

import christmas.domain.discount.Discount;
import christmas.domain.discount.context.DiscountContext;

public class DDayDiscount extends Discount {

    private static final int BASIC_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT_PER_DAY = 100;

    public DDayDiscount(DiscountContext context) {
        super(context, "크리스마스 디데이 할인");
    }

    @Override
    public int calculateDiscount(DiscountContext context) {
        int day = context.getReservationDate().getDayOfMonth();
        return BASIC_DISCOUNT_AMOUNT + ((day - 1) * DISCOUNT_AMOUNT_PER_DAY);
    }
}
