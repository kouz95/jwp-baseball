package baseball.game.ui;

import baseball.game.domain.Target;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetResponse {
    private final String target;

    public TargetResponse(@JsonProperty(value = "target") String target) {
        this.target = target;
    }

    public static TargetResponse of(String target) {
        return new TargetResponse(target);
    }

    public static TargetResponse of(Target target) {
        return new TargetResponse(target.toNumbers());
    }

    public String getTarget() {
        return target;
    }
}
