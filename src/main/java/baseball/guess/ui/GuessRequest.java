package baseball.guess.ui;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuessRequest {
    private final String guess;

    public GuessRequest(@JsonProperty(value = "guess") String guess) {
        this.guess = guess;
    }

    public String getGuess() {
        return guess;
    }
}
