package baseball.game.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseball.game.vo.BaseballNumber;
import baseball.game.vo.Target;
import baseball.game.vo.TargetDuplicatedException;

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
            try {
                numbers.add(new BaseballNumber(number));
            } catch (TargetDuplicatedException ignored) {}
        }

        return new Target(numbers);
    }
}
