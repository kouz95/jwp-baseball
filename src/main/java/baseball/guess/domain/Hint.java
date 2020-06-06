package baseball.guess.domain;

public class Hint {
    private final Long guessId;
    private final GuessResultType guessResultType;
    private final int strikeCount;
    private final int ballCount;

    public Hint(Long guessId, GuessResultType guessResultType, int strikeCount, int ballCount) {
        this.guessId = guessId;
        this.guessResultType = guessResultType;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public Long getGuessId() {
        return guessId;
    }

    public GuessResultType getGuessResultType() {
        return guessResultType;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }
}
