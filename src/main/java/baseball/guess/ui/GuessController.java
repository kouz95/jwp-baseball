package baseball.guess.ui;

import static baseball.guess.ui.GuessController.*;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baseball.guess.application.GuessService;
import baseball.guess.domain.Hint;

@RestController
@RequestMapping(GUESS_URI)
public class GuessController {
    public static final String GUESS_URI = "/guesses";

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<GuessResponse> guess(@PathVariable String gameId, @RequestBody GuessRequest guessRequest) {
        Hint hint = guessService.guess(gameId, guessRequest.getGuess());
        GuessResponse guessResponse = new GuessResponse(hint.getGuessResultType(), hint.getStrikeCount(), hint.getBallCount());
        return ResponseEntity.created(URI.create(GUESS_URI + "/" + gameId + "/" + hint.getGuessId())).body(guessResponse);
    }
}
