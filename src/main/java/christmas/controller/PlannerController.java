package christmas.controller;

import christmas.domain.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class PlannerController {

    public void run() {
        OutputView.printGreeting();

        LocalDate reservationDate = InputView.getReservationDate();
        Orders orders = InputView.getClientOrders();

    }

}
