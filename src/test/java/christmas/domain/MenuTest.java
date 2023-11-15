package christmas.domain;

import christmas.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MenuTest {

    @Test
    @DisplayName("메뉴 이름으로 검색")
    void getByNameValidMenu() {
        Menu menu = Menu.getByName("티본스테이크");
        assertThat(menu).isEqualTo(Menu.TBONE_STEAK);
    }

    @Test
    @DisplayName("잘못된 메뉴 이름으로 검색 시 예외 발생")
    void throwExceptionForInvalidMenuName() {
        String invalidMenuName = "메뉴판에없는메뉴";
        assertThatThrownBy(() -> Menu.getByName(invalidMenuName))
                .isInstanceOf(InvalidOrderException.class);
    }

}