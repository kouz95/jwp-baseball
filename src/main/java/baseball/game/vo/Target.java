package baseball.game.vo;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

import org.springframework.data.relational.core.mapping.MappedCollection;

public class Target {
    private static final int TARGET_SIZE = 3;
    private static final int SELF = 1;

    @MappedCollection(keyColumn = "baseball_game_key", idColumn = "baseball_game_id")
    private final List<BaseballNumber> baseballNumbers;

    public Target(List<BaseballNumber> baseballNumbers) {
        validate(baseballNumbers);
        this.baseballNumbers = baseballNumbers;
    }

    private void validate(List<BaseballNumber> baseballNumbers) {
        if (baseballNumbers.size() != TARGET_SIZE) {
            throw new TargetOutOfBoundException();
        }
        if (isDuplicated(baseballNumbers)) {
            throw new TargetDuplicatedException();
        }
    }

    private boolean isDuplicated(List<BaseballNumber> numbers) {
        return numbers.stream()
            .anyMatch(number -> hasAnotherEquals(number, numbers));
    }

    private boolean hasAnotherEquals(BaseballNumber current, List<BaseballNumber> numbers) {
        return numbers.stream()
            .filter(number -> number.equals(current))
            .count() > SELF;
    }

    public String toNumbers() {
        return baseballNumbers.stream()
            .mapToInt(BaseballNumber::getNumber)
            .mapToObj(String::valueOf)
            .collect(joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Target target = (Target)o;
        return Objects.equals(baseballNumbers, target.baseballNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseballNumbers);
    }
}
