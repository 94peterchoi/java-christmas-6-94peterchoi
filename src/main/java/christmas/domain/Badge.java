package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Badge {

    NONE("없음", (amount) -> amount < 5000),
    STAR("별", (amount) -> 5000 <= amount && amount < 10000),
    TREE("트리", (amount) -> 10000 <= amount && amount < 20000),
    SANTA("산타", (amount) -> 20000 <= amount);

    private static final List<Badge> BADGES = Arrays.asList(values());

    private final String name;
    private final Predicate<Integer> predicate;

    Badge(String name, Predicate<Integer> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public static Badge getBadge(int totalBenefitsAmount) {
        return BADGES.stream()
                .filter(badge -> badge.predicate.test(totalBenefitsAmount))
                .findFirst()
                .orElse(Badge.NONE);
    }

    public String getName() {
        return name;
    }

}
