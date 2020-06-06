package baseball.guess;

import static baseball.guess.ui.GuessController.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import baseball.game.GameAcceptanceTest;
import baseball.guess.ui.GuessResponse;

public class GuessAcceptanceTest extends GameAcceptanceTest {

    @TestFactory
    Stream<DynamicTest> guessAcceptance() {
        // given 목표 숫자가 설정 되어 있다.
        String location = startBaseball();
        String gameId = extractId(location);

        return Stream.of(
            dynamicTest("사용자 숫자 입력", () -> {
                // when 사용자가 숫자를 입력 한다.
                GuessResponse guessResponse = guessTarget(gameId);
                // then 입력에 대한 결과를 조회한다.
                assertThat(guessResponse.getStrikeCount()).isNotNull();
                assertThat(guessResponse.getBallCount()).isNotNull();
                assertThat(guessResponse.getGuessResultType()).isNotNull();
            })
        );
    }

    private GuessResponse guessTarget(String gameId) {
        Map<String, String> params = new HashMap<>();
        params.put("guess", "123");
        return given().
            log().all().
            contentType(MediaType.APPLICATION_JSON_VALUE).
            accept(MediaType.APPLICATION_JSON_VALUE).
            body(params).
            when().
            post(GUESS_URI + "/" + gameId).
            then().
            statusCode(HttpStatus.CREATED.value()).
            log().all().
            extract().as(GuessResponse.class);
    }
}
