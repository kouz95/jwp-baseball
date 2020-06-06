package baseball.game.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetTest {
    private BaseballNumber one;
    private BaseballNumber two;
    private BaseballNumber three;

    @BeforeEach
    void setUp() {
        one = new BaseballNumber(1);
        two = new BaseballNumber(2);
        three = new BaseballNumber(3);
    }

    @Test
    void create() {
        assertThat(new Target(null, Arrays.asList(one, two, three))).isInstanceOf(
            Target.class);
    }

    @Test
    void createFailsWhenOutOfBound() {
        BaseballNumber four = new BaseballNumber(4);
        assertThatThrownBy(() -> new Target(null, Arrays.asList(one, two, three, four)))
            .isInstanceOf(TargetOutOfBoundException.class);
    }

    @Test
    void createFailsWhenDuplicated() {
        BaseballNumber duplicated = new BaseballNumber(1);
        assertThatThrownBy(() -> new Target(null, Arrays.asList(one, two, duplicated)))
            .isInstanceOf(TargetDuplicatedException.class);
    }

    @Test
    void toNumbers() {
        Target target = new Target(null, Arrays.asList(one, two, three));
        assertThat(target.toNumbers()).isEqualTo("123");
    }
}