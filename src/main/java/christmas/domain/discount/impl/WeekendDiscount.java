package christmas.domain.discount.impl;

import christmas.domain.MenuType;
import christmas.domain.discount.Discount;
import christmas.domain.discount.context.DiscountContext;

public class WeekendDiscount extends Discount {

    private static final int DISCOUNT_AMOUNT = 2023;
    private static final MenuType DISCOUNT_TARGET_MENU = MenuType.MAIN;

    public WeekendDiscount(DiscountContext context) {
        super(context, "주말 할인");
    }

    @Override
    public int calculateDiscount(DiscountContext context) {
        int mainMenuCount = context.getOrders().getCountOfMenuType(DISCOUNT_TARGET_MENU);
        return mainMenuCount * DISCOUNT_AMOUNT;
    }

}
