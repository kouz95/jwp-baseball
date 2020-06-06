package baseball.guess.ui;

import baseball.guess.domain.GuessResultType;
import baseball.guess.domain.Hint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GuessResponse {
    private final GuessResultType guessResultType;
    private final int strikeCount;
    private final int ballCount;

    public GuessResponse(
        @JsonProperty(value = "guessResultType") GuessResultType guessResultType,
        @JsonProperty(value = "strikeCount") int strikeCount,
        @JsonProperty(value = "ballCount") int ballCount
    ) {
        this.guessResultType = guessResultType;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public static GuessResponse of(Hint hint) {
        return new GuessResponse(hint.getGuessResultType(), hint.getStrikeCount(), hint.getBallCount());
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
