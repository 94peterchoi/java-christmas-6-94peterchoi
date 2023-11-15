package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GiftsTest {

    @Test
    @DisplayName("12만원 이상의 주문 금액에 대해 샴페인 증정")
    void addChampagneForSufficientOrderAmount() {
        Gifts gifts = new Gifts();
        gifts.addGiftForOrderAmount(120000);
        assertThat(gifts.getCountOfGift(Menu.CHAMPAGNE)).isEqualTo(1);
    }

    @Test
    @DisplayName("12만원 미만의 주문 금액은 샴페인 증정 없음")
    void notAddChampagneForInsufficientOrderAmount() {
        Gifts gifts = new Gifts();
        gifts.addGiftForOrderAmount(100000);
        assertThat(gifts.getCountOfGift(Menu.CHAMPAGNE)).isNull();
    }

    @Test
    @DisplayName("증정메뉴 총 금액 계산")
    void calculateTotalAmountOfGifts() {
        Gifts gifts = new Gifts();
        gifts.addGiftForOrderAmount(120000);
        int expectedTotal = Menu.CHAMPAGNE.getPrice();
        assertThat(gifts.getTotalAmountOfGifts()).isEqualTo(expectedTotal);
    }

}