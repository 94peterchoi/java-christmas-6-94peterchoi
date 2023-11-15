package christmas.util;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.exception.InvalidOrderException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlannerUtil {

    public static Orders inputToOrders(String userInput) {
        List<Order> orders = Arrays.stream(userInput.trim().split(","))
                .map(input -> {
                    String[] orderInfo = input.split("-");
                    validateOrderInfo(orderInfo);
                    Menu menu = Menu.getByName(orderInfo[0]);
                    int count = NumberUtil.parseInteger(orderInfo[1]);
                    return new Order(menu, count);
                }).collect(Collectors.toList());

        return new Orders(orders);
    }

    private static void validateOrderInfo(String[] orderInfo) {
        if (orderInfo.length != 2) {
            throw new InvalidOrderException();
        }
    }

}
