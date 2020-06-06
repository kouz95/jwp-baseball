package baseball.game.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;

public class Target {
    private static final int TARGET_SIZE = 3;
    private static final int SELF = 1;

    private final AggregateReference<BaseballGame, Long> id;

    @MappedCollection(keyColumn = "number_index", idColumn = "baseball_game_id")
    private final List<BaseballNumber> baseballNumbers;

    public Target(AggregateReference<BaseballGame, Long> id, List<BaseballNumber> baseballNumbers) {
        this.id = id;
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

    public int calculateStrikeCount(String guessNumbers) {
        List<BaseballNumber> numbers = toBaseballNumbers(guessNumbers);

        int strikeCount = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).equals(baseballNumbers.get(i))) {
                strikeCount++;
            }
        }

        return strikeCount;
    }

    public int calculateBallCount(String guessNumbers) {
        List<BaseballNumber> numbers = toBaseballNumbers(guessNumbers);

        int ballCount = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (baseballNumbers.contains(numbers.get(i)) && !numbers.get(i).equals(baseballNumbers.get(i))) {
                ballCount++;
            }
        }

        return ballCount;
    }

    private List<BaseballNumber> toBaseballNumbers(String guessNumbers) {
        List<BaseballNumber> numbers = Arrays.stream(guessNumbers.split(""))
            .mapToInt(Integer::parseInt)
            .mapToObj(BaseballNumber::new)
            .collect(toList());
        validate(numbers);
        return numbers;
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
        return Objects.equals(this.baseballNumbers, target.baseballNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseballNumbers);
    }
}
