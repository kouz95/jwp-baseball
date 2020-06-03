package baseball.game.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import baseball.game.domain.BaseballGame;
import baseball.game.domain.BaseballNumber;
import baseball.game.domain.BaseballRepository;
import baseball.game.domain.Target;

@ExtendWith(MockitoExtension.class)
class BaseballServiceTest {
    @Mock
    private BaseballRepository baseballRepository;

    private BaseballService baseballService;

    @BeforeEach
    void setUp() {
        baseballService = new BaseballService(baseballRepository);
    }

    @Test
    void createGame() {
        when(baseballRepository.save(any())).thenReturn(new BaseballGame(1L,
            new Target(Arrays.asList(
                new BaseballNumber(1), new BaseballNumber(2), new BaseballNumber(3)))
            ));
        Long gameId = baseballService.createGame();

        assertThat(gameId).isNotNull();
    }
}