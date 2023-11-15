package christmas.domain.discount.impl;

import christmas.domain.discount.Discount;
import christmas.domain.discount.context.DiscountContext;

public class SpecialDiscount extends Discount {

    private static final int DISCOUNT_AMOUNT = 1000;

    public SpecialDiscount(DiscountContext context) {
        super(context, "특별 할인");
    }

    @Override
    public int calculateDiscount(DiscountContext context) {
        return DISCOUNT_AMOUNT;
    }
}
