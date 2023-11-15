package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Benefits;
import christmas.domain.Gifts;
import christmas.domain.Orders;
import christmas.domain.discount.Discounts;
import christmas.domain.discount.context.DiscountContext;

import java.time.LocalDate;

public class PlannerService {

    public Gifts generateGiftsForOrder(Orders orders) {
        int totalOrderAmountBeforeDiscounts = orders.getTotalOrderAmountBeforeDiscounts();
        Gifts gifts = new Gifts();
        gifts.addGiftForOrderAmount(totalOrderAmountBeforeDiscounts);

        return gifts;
    }

    public Discounts generateDiscountsForOrderAndDate(Orders orders, LocalDate reservationDate) {
        DiscountContext discountContext = new DiscountContext(orders, reservationDate);
        return new Discounts(discountContext);
    }

    public Benefits generateBenefits(Orders orders, LocalDate reservationDate) {
        Discounts discounts = generateDiscountsForOrderAndDate(orders, reservationDate);
        Gifts gifts = generateGiftsForOrder(orders);

        return new Benefits(discounts, gifts);
    }

    public Badge determineBadge(Benefits benefits) {
        return Badge.getBadge(benefits.calculateTotalBenefits());
    }

}
