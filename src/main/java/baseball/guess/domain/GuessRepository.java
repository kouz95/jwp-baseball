package baseball.guess.domain;

import org.springframework.data.repository.CrudRepository;

public interface GuessRepository extends CrudRepository<Guess, Long> {
}
