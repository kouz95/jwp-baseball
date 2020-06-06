package baseball.guess.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;

import baseball.game.domain.BaseballGame;
import baseball.game.domain.Target;

public class Guess {
    @Id
    private final Long id;
    @Embedded.Empty
    private final Target target;
    private final AggregateReference<BaseballGame, Long> baseballGameId;

    public Guess(Long id, Target target,
        AggregateReference<BaseballGame, Long> baseballGameId) {
        this.id = id;
        this.target = target;
        this.baseballGameId = baseballGameId;
    }

    public Guess withId(Long id) {
        return new Guess(id, this.target, this.baseballGameId);
    }

    public Long getId() {
        return id;
    }
}
