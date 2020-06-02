package baseball.game.vo;

public class InvalidBaseballNumberException extends RuntimeException {
    public InvalidBaseballNumberException() {
        super("숫자는 1 ~ 9 사이 이어야 합니다.");
    }
}
