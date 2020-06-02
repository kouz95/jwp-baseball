package baseball.game.vo;

public class TargetDuplicatedException extends RuntimeException {
    public TargetDuplicatedException() {
        super("목표 숫자는 중복될 수 없습니다.");
    }
}
