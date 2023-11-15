package christmas.util;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlannerUtilTest {

    @Test
    @DisplayName("유효한 주문 입력으로 Orders 객체 생성")
    void createOrdersFromValidInput() {
        String validOrderInput = "티본스테이크-2,시저샐러드-1";
        Orders orders = PlannerUtil.inputToOrders(validOrderInput);

        Order firstOrder = orders.getOrders().get(0);
        Menu firstOrderMenu = firstOrder.getMenu();
        int firstOrderCount = firstOrder.getCount();

        Order secondOrder = orders.getOrders().get(1);
        Menu secondOrderMenu = secondOrder.getMenu();
        int secondOrderCount = secondOrder.getCount();

        assertThat(firstOrderMenu).isEqualTo(Menu.TBONE_STEAK);
        assertThat(firstOrderCount).isEqualTo(2);

        assertThat(secondOrderMenu).isEqualTo(Menu.CAESAR_SALAD);
        assertThat(secondOrderCount).isEqualTo(1);
    }

    @Test
    @DisplayName("유효하지 않은 주문 입력으로 Orders 객체 생성 시 예외 발생")
    void throwExceptionForInvalidInput() {
        String invalidOrderInput = "티본스테이크,시저샐러드-1";
        assertThatThrownBy(() -> PlannerUtil.inputToOrders(invalidOrderInput))
                .isInstanceOf(InvalidOrderException.class);
    }

}