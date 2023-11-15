package christmas.domain;

import christmas.exception.InvalidOrderException;

public class Order {

    private static final int MINIMUM_ORDER_COUNT = 1;

    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;

        validateCount(count);
        this.count = count;
    }

    public void validateCount(int count) {
        if (count < MINIMUM_ORDER_COUNT) {
            throw new InvalidOrderException();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

}
