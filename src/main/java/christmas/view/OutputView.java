package christmas.view;

import christmas.domain.*;
import christmas.domain.discount.Discount;
import christmas.domain.discount.Discounts;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static christmas.constant.PlannerConsts.ITEM_UNIT;
import static christmas.constant.PlannerConsts.MONTH_OF_PLANNER;
import static christmas.util.StringUtil.formatCurrency;

public class OutputView {

    private static final int ZERO = 0;
    private static final String MINUS = "-";

    private static final String GREETING_MESSAGE = String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", MONTH_OF_PLANNER);
    private static final String PREVIEW_MESSAGE_ON_RESERVATION_DATE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String GIFT_EVENT = "증정 이벤트: ";
    private static final String NONE = "없음";
    private static final String TOTAL_COST_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String TOTAL_COST_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String TOTAL_BENEFIT_LIST_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String EVENT_BADGE_MESSAGE = String.format("<%d월 이벤트 배지>", MONTH_OF_PLANNER);

    public static void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

    public static void printPreviewMessageOnReservationDate(LocalDate reservationDate) {
        System.out.printf(PREVIEW_MESSAGE_ON_RESERVATION_DATE, MONTH_OF_PLANNER, reservationDate.getDayOfMonth());
    }

    public static void printOrderMenus(Orders orders) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (Order order : orders.getOrders()) {
            String menuName = order.getMenu().getName();
            int count = order.getCount();
            System.out.printf("%s %d%s%n", menuName, count, ITEM_UNIT);
        }
        System.out.println();
    }

    public static void printTotalOrderAmountBeforeDiscounts(Orders orders) {
        System.out.println(TOTAL_COST_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(formatCurrency(orders.getTotalOrderAmountBeforeDiscounts()));
        System.out.println();
    }

    public static void printGiftMenus(Gifts gifts) {
        System.out.println(GIFT_MENU_MESSAGE);
        if (gifts.getTotalAmountOfGifts() == ZERO) {
            System.out.println(NONE);
        } else {
            String giftDetails = gifts.getGifts().keySet().stream()
                    .map(gift -> gift.getName() + " " + gifts.getCountOfGift(gift) + ITEM_UNIT)
                    .collect(Collectors.joining("\n"));
            System.out.println(giftDetails);
        }
        System.out.println();
    }

    public static void printTotalBenefits(Benefits benefits) {
        System.out.println(TOTAL_BENEFIT_LIST_MESSAGE);

        Discounts discounts = benefits.getDiscounts();
        Gifts gifts = benefits.getGifts();

        printDiscounts(discounts);
        printGifts(gifts);

        if (discounts.getTotalDiscountAmount() == 0 && gifts.getTotalAmountOfGifts() == 0) {
            System.out.println(NONE);
        }

        System.out.println();
    }

    public static void printDiscounts(Discounts discounts) {
        for (Discount discount : discounts.getDiscounts()) {
            System.out.println(discount.getDiscountDetail() + ": " + MINUS + formatCurrency(discount.getCalculatedDiscount()));
        }
    }

    public static void printGifts(Gifts gifts) {
        int totalAmountOfGifts = gifts.getTotalAmountOfGifts();
        if (totalAmountOfGifts > 0) {
            System.out.println(GIFT_EVENT + MINUS + formatCurrency(totalAmountOfGifts));
        }
    }

    public static void printTotalBenefitsAmount(Benefits benefits) {
        int totalBenefits = benefits.calculateTotalBenefits();
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE);
        if (totalBenefits > 0) {
            System.out.println(MINUS + formatCurrency(totalBenefits));
        } else {
            System.out.println(formatCurrency(totalBenefits));
        }
        System.out.println();
    }

    public static void printTotalOrderAmountAfterDiscounts(Orders orders, Discounts discounts) {
        System.out.println(TOTAL_COST_AFTER_DISCOUNT_MESSAGE);
        int totalCostAfterDiscounts = orders.getTotalOrderAmountAfterDiscounts(discounts.getTotalDiscountAmount());
        System.out.println(formatCurrency(totalCostAfterDiscounts));
        System.out.println();
    }

    public static void printBadge(Badge badge) {
        System.out.println(EVENT_BADGE_MESSAGE);
        System.out.print(badge.getName());
    }

}
