package baseball.game;

import org.springframework.stereotype.Service;

import baseball.game.dto.TargetResponse;
import baseball.game.factory.RandomTargetStrategy;
import baseball.game.factory.TargetFactory;
import baseball.game.vo.Target;

@Service
public class BaseballService {
    private final BaseballRepository baseballRepository;

    public BaseballService(BaseballRepository baseballRepository) {
        this.baseballRepository = baseballRepository;
    }

    public Long createGame() {
        Target target = new TargetFactory(new RandomTargetStrategy()).create();
        BaseballGame persist = baseballRepository.save(new BaseballGame(null, target));
        return persist.getId();
    }

    public TargetResponse showAnswer(String gameId) {
        return TargetResponse.of("123");
    }
}
