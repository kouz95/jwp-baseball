package baseball.game.factory;

import baseball.game.vo.Target;

public class TargetFactory {
    private final TargetGenerateStrategy targetGenerateStrategy;

    public TargetFactory(TargetGenerateStrategy targetGenerateStrategy) {
        this.targetGenerateStrategy = targetGenerateStrategy;
    }

    public Target create() {
        return targetGenerateStrategy.generate();
    }
}
