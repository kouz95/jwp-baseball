package baseball.game.domain;

import org.springframework.data.repository.CrudRepository;

public interface BaseballRepository extends CrudRepository<BaseballGame, Long> {

}
