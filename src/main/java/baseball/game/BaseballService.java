package baseball.game;

import org.springframework.stereotype.Service;

import baseball.game.dto.TargetResponse;

@Service
public class BaseballService {
    public Long createGame() {
        return 1L;
    }

    public TargetResponse showAnswer(String gameId) {
        return TargetResponse.of("123");
    }
}
