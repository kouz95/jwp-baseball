package baseball.dto;

public class BaseballAnswerResponse {
    private String answer;

    private BaseballAnswerResponse() {
    }

    public BaseballAnswerResponse(String answer) {
        this.answer = answer;
    }

    public static BaseballAnswerResponse of(String answer) {
        return new BaseballAnswerResponse(answer);
    }

    public String getAnswer() {
        return answer;
    }
}
