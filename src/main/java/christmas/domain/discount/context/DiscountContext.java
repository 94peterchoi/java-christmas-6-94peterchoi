package christmas.domain.discount.context;

import christmas.domain.Orders;

import java.time.LocalDate;

public class DiscountContext {
    private final Orders orders;
    private final LocalDate reservationDate;

    public DiscountContext(Orders orders, LocalDate reservationDate) {
        this.orders = orders;
        this.reservationDate = reservationDate;
    }

    public Orders getOrders() {
        return orders;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

}
