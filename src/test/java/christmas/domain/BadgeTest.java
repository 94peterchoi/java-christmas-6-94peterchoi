package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BadgeTest {

    @Test
    @DisplayName("혜택 금액이 없을 때 '없음' 반환")
    void getNoneForLowAmount() {
        assertThat(Badge.getBadge(0)).isEqualTo(Badge.NONE);
        assertThat(Badge.getBadge(4999)).isEqualTo(Badge.NONE);
    }

    @Test
    @DisplayName("혜택 금액에 따른 '별' 배지 반환")
    void getStarBadge() {
        assertThat(Badge.getBadge(5000)).isEqualTo(Badge.STAR);
        assertThat(Badge.getBadge(9999)).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("혜택 금액에 따른 '트리' 배지 반환")
    void getTreeBadge() {
        assertThat(Badge.getBadge(10000)).isEqualTo(Badge.TREE);
        assertThat(Badge.getBadge(19999)).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("혜택 금액이 충분히 높을 때 '산타' 배지 반환")
    void getSantaBadge() {
        assertThat(Badge.getBadge(20000)).isEqualTo(Badge.SANTA);
        assertThat(Badge.getBadge(30000)).isEqualTo(Badge.SANTA);
    }

}