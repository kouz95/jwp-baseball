package baseball;

import static baseball.BaseballController.*;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baseball.dto.BaseballAnswerResponse;

@RestController
@RequestMapping(BASEBALL_URI)
public class BaseballController {
    public static final String BASEBALL_URI = "/baseballs";

    private final BaseballService baseballService;

    public BaseballController(BaseballService baseballService) {
        this.baseballService = baseballService;
    }

    @PostMapping
    public ResponseEntity<Void> createGame() {
        Long gameId = baseballService.createGame();
        return ResponseEntity.created(URI.create(BASEBALL_URI + "/" + gameId)).build();
    }

    @GetMapping("/{id}/answer")
    public ResponseEntity<BaseballAnswerResponse> showAnswer(@PathVariable String id) {
        BaseballAnswerResponse baseballAnswerResponse = baseballService.showAnswer(id);
        return ResponseEntity.ok(baseballAnswerResponse);
    }
}
