package baseball.game.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 9})
    void create(int number) {
        assertThat(new BaseballNumber(number)).isInstanceOf(BaseballNumber.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void createWhenOutOfBound(int number) {
        assertThatThrownBy(() -> new BaseballNumber(number))
            .isInstanceOf(InvalidBaseballNumberException.class);
    }
}