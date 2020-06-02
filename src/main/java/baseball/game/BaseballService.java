package baseball.game;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import baseball.game.dto.TargetResponse;
import baseball.game.vo.BaseballNumber;
import baseball.game.vo.Target;

@Service
public class BaseballService {
    private final BaseballRepository baseballRepository;

    public BaseballService(BaseballRepository baseballRepository) {
        this.baseballRepository = baseballRepository;
    }

    public Long createGame() {
        BaseballGame baseballGame = new BaseballGame(null, new Target(Arrays.asList(new BaseballNumber(1), new BaseballNumber(2), new BaseballNumber(3))));
        BaseballGame persist = baseballRepository.save(baseballGame);
        return persist.getId();
    }

    public TargetResponse showAnswer(String gameId) {
        return TargetResponse.of("123");
    }
}
