package christmas;

import christmas.controller.PlannerController;
import christmas.service.PlannerService;

public class Application {
    public static void main(String[] args) {
        PlannerController plannerController = new PlannerController(new PlannerService());
        plannerController.run();
    }
}
