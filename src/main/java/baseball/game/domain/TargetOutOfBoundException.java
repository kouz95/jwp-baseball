package baseball.game.domain;

public class TargetOutOfBoundException extends RuntimeException {
    public TargetOutOfBoundException() {
        super("목표 숫자는 3자리 이어야 합니다.");
    }
}
