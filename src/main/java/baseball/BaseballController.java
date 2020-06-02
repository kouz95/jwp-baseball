package baseball;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baseball")
public class BaseballController {
    private final BaseballService baseballService;

    public BaseballController(BaseballService baseballService) {
        this.baseballService = baseballService;
    }

    @PostMapping
    public ResponseEntity<Void> createGame() {
        Long gameId = baseballService.createGame();
        return ResponseEntity.created(URI.create("/baseball/" + gameId)).build();
    }
}
