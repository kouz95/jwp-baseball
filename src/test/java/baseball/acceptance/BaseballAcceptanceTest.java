package baseball.acceptance;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class BaseballAcceptanceTest {
    @TestFactory
    Stream<DynamicTest> acceptance() {
        return Stream.of(
            dynamicTest("게임 시작", () -> {
                // when 게임 시작을 요청한다.
                String location = startBaseball();
                // then 게임이 시작된다.
                showBaseball(location);
            })
        );
    }

    private String startBaseball() {
        return given().
            when().
            post("/baseball").
            then().
            log().all().
            statusCode(HttpStatus.CREATED.value()).
            extract().header(HttpHeaders.LOCATION);
    }

    private void showBaseball(String location) {
        List<String> path = Arrays.asList(location.split("/"));
        int lastIndex = path.size() - 1;

        String gameId = path.get(lastIndex);

        given().
            when().
            get("/baseball" + "/" + gameId).
            then().
            log().all().
            statusCode(HttpStatus.OK.value());
    }
}
