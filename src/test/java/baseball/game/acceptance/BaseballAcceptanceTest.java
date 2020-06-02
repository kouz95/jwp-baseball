package baseball.game.acceptance;

import static baseball.game.BaseballController.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import baseball.game.dto.TargetResponse;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BaseballAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @TestFactory
    Stream<DynamicTest> acceptance() {
        // given 게임이 생성 되어 있다.
        String location = startBaseball();

        return Stream.of(
            dynamicTest("게임 시작", () -> {
                // when 게임 시작을 요청한다.
                // then 게임이 생성 되었다.
                assertThat(extractId(location)).isNotEqualTo("null");
            }),
            dynamicTest("정답 조회", () -> {
                // 정답 조회를 요청한다.
                TargetResponse response = showAnswer(extractId(location));
                // 정답을 조회한다.
                assertThat(response.getTarget()).isNotNull();
            })
        );
    }

    private String startBaseball() {
        return given().
            log().all().
            when().
            post(BASEBALL_URI).
            then().
            log().all().
            statusCode(HttpStatus.CREATED.value()).
            extract().header(HttpHeaders.LOCATION);
    }

    private TargetResponse showAnswer(String gameId) {
        return given().
            log().all().
            accept(MediaType.APPLICATION_JSON_VALUE).
            when().
            get(BASEBALL_URI + "/" + gameId + "/answer").
            then().
            log().all().
            extract().as(TargetResponse.class);
    }

    private String extractId(String location) {
        List<String> path = Arrays.asList(location.split("/"));
        int lastIndex = path.size() - 1;

        return path.get(lastIndex);
    }
}
