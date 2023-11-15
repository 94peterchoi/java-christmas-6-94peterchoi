package christmas.service;

import christmas.domain.*;
import christmas.domain.discount.Discounts;
import christmas.util.DateUtil;
import christmas.util.PlannerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlannerServiceTest {

    private Orders orders;
    private PlannerService plannerService;

    @BeforeEach
    void setUp() {
        String validOrderInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        orders = PlannerUtil.inputToOrders(validOrderInput);

        plannerService = new PlannerService();
    }

    @Test
    @DisplayName("주문에 따른 선물 생성")
    void generateGiftsForOrders() {
        Gifts gifts = plannerService.generateGiftsForOrder(orders);
        gifts.getCountOfGift(Menu.CHAMPAGNE);

        assertThat(orders.getTotalOrderAmountBeforeDiscounts()).isEqualTo(142000);
        assertThat(gifts.getCountOfGift(Menu.CHAMPAGNE)).isEqualTo(1);
    }

    @Test
    @DisplayName("주문 및 방문날짜에 따른 할인 검증")
    void generateDiscountsForOrdersAndDate() {
        LocalDate reservationDate = DateUtil.inputToLocalDate("3");
        Discounts generatedDiscounts = plannerService.generateDiscountsForOrderAndDate(orders, reservationDate);

        assertThat(generatedDiscounts.getTotalDiscountAmount()).isEqualTo(6246);
    }

    @Test
    @DisplayName("주문 및 날짜에 따른 혜택 검증")
    void generateBenefits() {
        LocalDate reservationDate = DateUtil.inputToLocalDate("3");
        Benefits generatedBenefits = plannerService.generateBenefits(orders, reservationDate);

        assertThat(generatedBenefits.calculateTotalBenefits()).isEqualTo(31246);
    }

    @Test
    @DisplayName("혜택에 따른 배지 검증")
    void determineBadge() {
        LocalDate reservationDate = DateUtil.inputToLocalDate("3");
        Benefits generatedBenefits = plannerService.generateBenefits(orders, reservationDate);

        Badge badge = plannerService.determineBadge(generatedBenefits);

        assertThat(badge).isEqualTo(Badge.SANTA);
    }

}