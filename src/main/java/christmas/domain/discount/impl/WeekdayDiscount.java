package christmas.domain.discount.impl;

import christmas.domain.MenuType;
import christmas.domain.discount.Discount;
import christmas.domain.discount.context.DiscountContext;

public class WeekdayDiscount extends Discount {

    private static final int DISCOUNT_AMOUNT = 2023;
    private static final MenuType DISCOUNT_TARGET_MENU = MenuType.DESSERT;

    public WeekdayDiscount(DiscountContext context) {
        super(context, "평일 할인");
    }

    @Override
    public int calculateDiscount(DiscountContext context) {
        int dessertCount = context.getOrders().getCountOfMenuType(DISCOUNT_TARGET_MENU);
        return dessertCount * DISCOUNT_AMOUNT;
    }
}
