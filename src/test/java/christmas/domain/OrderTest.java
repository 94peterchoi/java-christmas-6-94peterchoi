package christmas.domain;

import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @DisplayName("정상 주문 생성")
    @Test
    void createValidOrder() {
        Menu menu = Menu.TBONE_STEAK;
        int count = 2;
        Order order = new Order(menu, count);

        assertThat(menu).isEqualTo(order.getMenu());
        assertThat(count).isEqualTo(order.getCount());
    }

    @DisplayName("비정상 주문일 시 예외 발생")
    @Test
    void throwExceptionForInvalidOrderCount() {
        Menu menu = Menu.TBONE_STEAK;
        int count = 0;
        assertThatThrownBy(() -> new Order(menu, count))
                .isInstanceOf(InvalidOrderException.class);
    }

    @DisplayName("주문한 메뉴의 종류 검증")
    @Test
    void checkMenuType() {
        Order order = new Order(Menu.TBONE_STEAK, 1);   // 티본스테이크 - 메인메뉴

        assertThat(order.isMenuTypeOf(MenuType.MAIN)).isTrue();
        assertThat(order.isMenuTypeOf(MenuType.DRINK)).isFalse();
    }

    @DisplayName("주문 비용 검증")
    @Test
    void calculateCorrectCost() {
        Menu menu = Menu.TBONE_STEAK; // 티본스테이크 가격 - 55000
        int count = 2;

        Order order = new Order(menu, count);
        int expectedCost = menu.getPrice() * count;

        assertThat(expectedCost).isEqualTo(order.calculateCost());
    }

}