package baseball.guess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GuessResultType {
    MATCH(String::equals),
    NO_MATCH((target, guess) -> !target.equals(guess));

    private final BiPredicate<String, String> judge;

    GuessResultType(BiPredicate<String, String> judge) {
        this.judge = judge;
    }

    public static GuessResultType of(String target, String guess) {
        return Arrays.stream(GuessResultType.values())
            .filter(value -> value.judge.test(target, guess))
            .findFirst()
            .orElseThrow(AssertionError::new);
    }
}
