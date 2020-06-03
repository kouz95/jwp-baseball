package baseball.guess.ui;

import static baseball.guess.ui.GuessController.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import baseball.guess.domain.GuessResultType;

@RestController
@RequestMapping(GUESS_URI)
public class GuessController {
    public static final String GUESS_URI = "/guesses";

    @PostMapping("/{gameId}")
    public ResponseEntity<GuessResponse> guess(@PathVariable String gameId, @RequestBody GuessRequest guessRequest) {
        GuessResponse guessResponse = new GuessResponse(GuessResultType.NO_MATCH, 2, 1);
        return ResponseEntity.created(URI.create(GUESS_URI + "/" + gameId + "/" + "1")).body(guessResponse);
    }

    @GetMapping
    public ResponseEntity<List<GuessResponse>> showGuesses(@RequestParam("gameId") String gameId) {
        return ResponseEntity.ok(Collections.singletonList(new GuessResponse(GuessResultType.NO_MATCH, 2, 1)));
    }
}
