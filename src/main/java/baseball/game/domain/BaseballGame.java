package baseball.game.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

public class BaseballGame {
    @Id
    private final Long id;

    @Embedded.Empty
    private final Target target;

    public BaseballGame(Long id, Target target) {
        this.id = id;
        this.target = target;
    }

    public BaseballGame withId(Long id) {
        return new BaseballGame(id, this.target);
    }

    public Long getId() {
        return id;
    }

    public Target getTarget() {
        return target;
    }
}
