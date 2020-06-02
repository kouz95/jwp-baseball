package baseball.game.vo;

import baseball.game.exception.InvalidBaseballNumberException;

public class BaseballNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    private final int number;

    public BaseballNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new InvalidBaseballNumberException();
        }
        this.number = number;
    }
}