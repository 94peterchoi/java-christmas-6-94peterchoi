package christmas.domain;

import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {

    @Test
    @DisplayName("유효한 주문 목록으로 Orders 객체 생성")
    void createValidOrders() {
        Order validOrder = new Order(Menu.TBONE_STEAK, 2);
        Orders orders = new Orders(Arrays.asList(validOrder));

        assertThat(orders.getOrders()).containsExactly(validOrder);
    }

    @Test
    @DisplayName("중복 주문이 포함된 경우 예외 발생")
    void throwExceptionForDuplicateOrders() {
        Order firstOrder = new Order(Menu.TBONE_STEAK, 1);
        Order secondOrder = new Order(Menu.TBONE_STEAK, 2);

        assertThatThrownBy(() -> new Orders(Arrays.asList(firstOrder, secondOrder)))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("음료만 주문한 경우 예외 발생")
    void throwExceptionForDrinkOnlyOrder() {
        Order drinkOrder = new Order(Menu.CHAMPAGNE, 1);

        assertThatThrownBy(() -> new Orders(Collections.singletonList(drinkOrder)))
                .isInstanceOf(InvalidOrderException.class);
    }

    @Test
    @DisplayName("주문 개수 초과 시 예외 발생")
    void throwExceptionForExceedingMaximumOrderCount() {
        Order order = new Order(Menu.TBONE_STEAK, 21);

        assertThatThrownBy(() -> new Orders(Collections.singletonList(order)))
                .isInstanceOf(InvalidOrderException.class);
    }


}