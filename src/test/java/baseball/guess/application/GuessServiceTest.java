package baseball.guess.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import baseball.game.application.BaseballService;
import baseball.game.domain.BaseballNumber;
import baseball.game.domain.Target;
import baseball.guess.domain.Guess;
import baseball.guess.domain.GuessRepository;
import baseball.guess.domain.GuessResultType;
import baseball.guess.domain.Hint;

@ExtendWith(value = MockitoExtension.class)
class GuessServiceTest {
    @Mock
    private GuessRepository guessRepository;
    @Mock
    private BaseballService baseballService;

    private GuessService guessService;

    @BeforeEach
    void setUp() {
        guessService = new GuessService(guessRepository, baseballService);
    }

    @Test
    void guess() {
        when(baseballService.showTarget(any())).thenReturn(new Target(
            Arrays.asList(new BaseballNumber(1), new BaseballNumber(2), new BaseballNumber(3))));
        when(guessRepository.save(any())).thenReturn(new Guess(1L, new Target(
            Arrays.asList(new BaseballNumber(1), new BaseballNumber(2), new BaseballNumber(4))), AggregateReference.to(1L)));

        Hint hint = guessService.guess("1", "124");
        assertThat(hint.getGuessId()).isEqualTo(1L);
        assertThat(hint.getGuessResultType()).isEqualTo(GuessResultType.NO_MATCH);
        assertThat(hint.getStrikeCount()).isEqualTo(2);
        assertThat(hint.getBallCount()).isEqualTo(0);
    }
}