package lotto.domain.strategy;

import lotto.domain.GenerateLottoNumberStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerateStrategy implements GenerateLottoNumberStrategy {
    public static final int LOTTERY_MIN_NUMBER = 1;
    public static final int LOTTERY_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = IntStream.rangeClosed(LOTTERY_MIN_NUMBER, LOTTERY_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());
    }
}