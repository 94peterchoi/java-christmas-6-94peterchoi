package christmas.view;

import static christmas.constant.PlannerConsts.MONTH_OF_PLANNER;

public class OutputView {

    private static final String GREETING_MESSAGE = String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", MONTH_OF_PLANNER);

    public static void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }


}
