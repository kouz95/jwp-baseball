package baseball.game.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseball.game.domain.BaseballNumber;
import baseball.game.domain.Target;
import baseball.game.domain.TargetGenerateStrategy;

public class RandomTargetStrategy implements TargetGenerateStrategy {
    private static final int MAX_NUMBER = 9;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_SIZE = 3;

    @Override
    public Target generate() {
        Random random = new Random();
        List<BaseballNumber> numbers = new ArrayList<>();

        while (numbers.size() < MAX_SIZE) {
            int number = random.nextInt(MAX_NUMBER) + MIN_NUMBER;
            BaseballNumber baseballNumber = new BaseballNumber(number);
            if (!numbers.contains(baseballNumber)) {
                numbers.add(baseballNumber);
            }
        }

        return new Target(numbers);
    }
}
