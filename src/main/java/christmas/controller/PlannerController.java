package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Benefits;
import christmas.domain.Orders;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    public void run() {
        OutputView.printGreeting();

        LocalDate reservationDate = InputView.getReservationDate();
        Orders orders = InputView.getClientOrders();
        Benefits benefits = plannerService.generateBenefits(orders, reservationDate);
        Badge badge = plannerService.determineBadge(benefits);

        displayResults();
    }

}
