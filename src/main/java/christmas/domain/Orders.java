package christmas.domain;

import christmas.exception.InvalidOrderException;

import java.util.List;

public class Orders {

    private static final int REQUIRED_FOOD_ORDER_COUNT = 1;
    private static final int MAXIMUM_ORDER_COUNT = 20;

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    private void validateOrders(List<Order> orders) {
        if (getCountOfOrdersWithoutDuplicates(orders) != orders.size()) {
            throw new InvalidOrderException();
        }

        if (getCountOfAllOrdersExceptSpecificMenuType(orders, MenuType.DRINK) < REQUIRED_FOOD_ORDER_COUNT) {
            throw new InvalidOrderException();
        }

        if (getCountOfAllOrders(orders) > MAXIMUM_ORDER_COUNT) {
            throw new InvalidOrderException();
        }
    }

    public int getCountOfOrdersWithoutDuplicates(List<Order> orders) {
        return (int) orders.stream()
                .map(Order::getMenu)
                .distinct()
                .count();
    }

    public int getCountOfAllOrders(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getCount)
                .sum();
    }

    public int getCountOfAllOrdersExceptSpecificMenuType(List<Order> orders, MenuType menuType) {
        return orders.stream()
                .filter(order -> !order.isMenuTypeOf(menuType))
                .mapToInt(Order::getCount)
                .sum();
    }

    public int getCountOfMenuType(MenuType menuType) {
        return this.orders.stream()
                .filter(order -> order.isMenuTypeOf(menuType))
                .mapToInt(Order::getCount)
                .sum();
    }

    public int getTotalOrderAmountBeforeDiscounts() {
        return this.orders.stream()
                .mapToInt(Order::calculateCost)
                .sum();
    }

    public int getTotalOrderAmountAfterDiscounts(int totalDiscountAmount) {
        return getTotalOrderAmountBeforeDiscounts() - totalDiscountAmount;
    }

    public List<Order> getOrders() {
        return orders;
    }



}
