package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Gifts {

    private static final int MIN_ORDER_AMOUNT_FOR_CHAMPAGNE = 120000;

    private final Map<Menu, Integer> gifts = new HashMap<>();

    public void addGiftForOrderAmount(int totalOrderAmount) {
        if (totalOrderAmount >= MIN_ORDER_AMOUNT_FOR_CHAMPAGNE) {
            gifts.merge(Menu.CHAMPAGNE, 1, Integer::sum);
        }
    }

    public Map<Menu, Integer> getGifts() {
        return gifts;
    }

    public Integer getCountOfGift(Menu menu) {
        return gifts.get(menu);
    }

}
