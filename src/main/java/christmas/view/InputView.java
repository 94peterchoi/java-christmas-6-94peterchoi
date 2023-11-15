package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Orders;
import christmas.util.DateUtil;
import christmas.util.PlannerUtil;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

import static christmas.constant.PlannerConsts.MONTH_OF_PLANNER;

public class InputView {

    private static final String PROMPT_FOR_RESERVATION_DATE = String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)", MONTH_OF_PLANNER);
    private static final String PROMPT_FOR_CLIENT_ORDERS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private static <T> T getInput(Supplier<String> messageSupplier, Function<String, T> inputProcessor) {
        while (true) {
            try {
                System.out.println(messageSupplier.get());
                String input = Console.readLine();
                return inputProcessor.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static LocalDate getReservationDate() {
        return getInput(
                () -> PROMPT_FOR_RESERVATION_DATE,
                DateUtil::inputToLocalDate
        );
    }

    public static Orders getClientOrders() {
        return getInput(
                () -> PROMPT_FOR_CLIENT_ORDERS,
                PlannerUtil::inputToOrders
        );
    }


}
