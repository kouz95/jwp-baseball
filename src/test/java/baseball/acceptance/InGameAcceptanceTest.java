package baseball.acceptance;

import static baseball.BaseballController.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.MediaType;

import baseball.dto.BaseballAnswerResponse;


public class InGameAcceptanceTest extends BaseballAcceptanceTest {
    @TestFactory
    Stream<DynamicTest> inGame() {
        // given 게임이 생성 되어 있다.
        String location = startBaseball();

        return Stream.of(
            dynamicTest("정답 조회", () -> {
                // 정답 조회를 요청한다.
                BaseballAnswerResponse response = showAnswer(extractId(location));
                // 정답을 조회한다.
                assertThat(response.getAnswer()).isNotNull();
            })
        );
    }

    private BaseballAnswerResponse showAnswer(String gameId) {
        return given().
            log().all().
            accept(MediaType.APPLICATION_JSON_VALUE).
            when().
            get(BASEBALL_URI + "/" + gameId + "/answer").
            then().
            log().all().
            extract().as(BaseballAnswerResponse.class);
    }
}
