package christmas.domain.discount;

import christmas.domain.discount.context.DiscountContext;

public abstract class Discount {

    protected int calculatedDiscount = 0;
    protected String discountDetail = "";

    public Discount(DiscountContext context, String discountDetail) {
        this.calculatedDiscount = calculateDiscount(context);
        this.discountDetail = discountDetail;
    }

    abstract public int calculateDiscount(DiscountContext discountContext);

    public int getCalculatedDiscount() {
        return calculatedDiscount;
    }

    public String getDiscountDetail() {
        return discountDetail;
    }

}
