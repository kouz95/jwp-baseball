package baseball.game.domain;

public class TargetFactory {
    private final TargetGenerateStrategy targetGenerateStrategy;

    public TargetFactory(TargetGenerateStrategy targetGenerateStrategy) {
        this.targetGenerateStrategy = targetGenerateStrategy;
    }

    public Target create() {
        return targetGenerateStrategy.generate();
    }
}
