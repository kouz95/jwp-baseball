package baseball;

import org.springframework.stereotype.Service;

import baseball.dto.BaseballAnswerResponse;

@Service
public class BaseballService {
    public Long createGame() {
        return 1L;
    }

    public BaseballAnswerResponse showAnswer(String gameId) {
        return BaseballAnswerResponse.of("123");
    }
}
