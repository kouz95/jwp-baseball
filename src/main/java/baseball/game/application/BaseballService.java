package baseball.game.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import baseball.game.domain.BaseballGame;
import baseball.game.domain.BaseballRepository;
import baseball.game.domain.RandomTargetStrategy;
import baseball.game.domain.Target;
import baseball.game.domain.TargetFactory;

@Service
public class BaseballService {
    private final BaseballRepository baseballRepository;

    public BaseballService(BaseballRepository baseballRepository) {
        this.baseballRepository = baseballRepository;
    }

    @Transactional
    public Long createGame() {
        Target target = new TargetFactory(new RandomTargetStrategy()).create();
        BaseballGame persist = baseballRepository.save(new BaseballGame(null, target));
        return persist.getId();
    }

    @Transactional(readOnly = true)
    public Target showTarget(String gameId) {
        BaseballGame baseballGame = baseballRepository.findById(Long.parseLong(gameId))
            .orElseThrow(RuntimeException::new);

        return baseballGame.getTarget();
    }
}
