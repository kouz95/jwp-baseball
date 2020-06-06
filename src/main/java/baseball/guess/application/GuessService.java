package baseball.guess.application;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import baseball.game.application.BaseballService;
import baseball.game.domain.Target;
import baseball.guess.domain.Guess;
import baseball.guess.domain.GuessRepository;
import baseball.guess.domain.GuessResultType;
import baseball.guess.domain.Hint;

@Service
public class GuessService {
    private final GuessRepository guessRepository;
    private final BaseballService baseballService;

    public GuessService(GuessRepository guessRepository, BaseballService baseballService) {
        this.guessRepository = guessRepository;
        this.baseballService = baseballService;
    }

    public Hint guess(String gameId, String guessNumbers) {
        Target target = baseballService.showTarget(gameId);
        Guess persist = guessRepository.save(new Guess(null, target, AggregateReference.to(Long.parseLong(gameId))));

        GuessResultType guessResultType = GuessResultType.of(target.toNumbers(), guessNumbers);
        int strikeCount = target.calculateStrikeCount(guessNumbers);
        int ballCount = target.calculateBallCount(guessNumbers);

        return new Hint(persist.getId(), guessResultType, strikeCount, ballCount);
    }
}
