package baseball.game.dto;

import baseball.game.vo.Target;

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

    public static TargetResponse of(Target target) {
        return new TargetResponse(target.toNumbers());
    }

    public String getTarget() {
        return target;
    }
}
