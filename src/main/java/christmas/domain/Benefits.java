package christmas.domain;

import christmas.domain.discount.Discounts;

public class Benefits {
    private final Discounts discounts;
    private final Gifts gifts;

    public Benefits(Discounts discounts, Gifts gifts) {
        this.discounts = discounts;
        this.gifts = gifts;
    }

    public int calculateTotalBenefits() {
        return gifts.getTotalAmountOfGifts() + discounts.getTotalDiscountAmount();
    }

    public Gifts getGifts() {
        return gifts;
    }

    public Discounts getDiscounts() {
        return discounts;
    }

}
