package baseball.game.dto;

import baseball.game.vo.Target;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetResponse {
    private final String target;

    public TargetResponse(@JsonProperty(value = "target") String target) {
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
