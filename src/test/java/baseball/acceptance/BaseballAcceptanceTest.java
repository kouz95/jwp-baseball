package baseball.acceptance;

import static baseball.BaseballController.*;
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
        return Stream.of(
            dynamicTest("게임 시작", () -> {
                // when 게임 시작을 요청한다.
                String location = startBaseball();
                // then 게임이 생성 되었다.
                assertThat(extractId(location)).isNotEqualTo("null");
            })
        );
    }

    protected String startBaseball() {
        return given().
            log().all().
            when().
            post(BASEBALL_URI).
            then().
            log().all().
            statusCode(HttpStatus.CREATED.value()).
            extract().header(HttpHeaders.LOCATION);
    }

    private void showBaseball(String location) {
        String gameId = extractId(location);

        given().
            when().
            get(BASEBALL_URI + "/" + gameId).
            then().
            log().all().
            statusCode(HttpStatus.OK.value());
    }

    protected String extractId(String location) {
        List<String> path = Arrays.asList(location.split("/"));
        int lastIndex = path.size() - 1;

        return path.get(lastIndex);
    }
}
