package baseball.game.dto;

public class TargetResponse {
    private String target;

    private TargetResponse() {
    }

    public TargetResponse(String target) {
        this.target = target;
    }

    public static TargetResponse of(String answer) {
        return new TargetResponse(answer);
    }

    public String getTarget() {
        return target;
    }
}
