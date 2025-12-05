package dnt.adventofcode.day5;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Part1Test extends TestBase {
    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("/day5test.txt", 3),
                Arguments.of("/day5real.txt", 607)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException {
        List<long[]> ranges = new ArrayList<>();
        Set<Long> ingredients = new HashSet<>();

        try (InputStream inputStream = Day5Part1Test.class.getResourceAsStream(input)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                if(line.contains("-")) {
                    // Ranges
                    String[] split = line.split("-");
                    ranges.add(new long[] {
                            Long.parseLong(split[0]),
                            Long.parseLong(split[1])
                    });
                }
                else if(!line.trim().isEmpty()) {
                    ingredients.add(Long.parseLong(line.trim()));
                }
            });
        }
        System.out.println(ranges);
        System.out.println(ingredients);

        int count = getCount(ingredients, ranges);
        assertThat(count).isEqualTo(expected);
    }

    private static int getCount(Set<Long> ingredients, List<long[]> ranges)
    {
        AtomicInteger count = new AtomicInteger(0);
        for (Long ingredient : ingredients)
        {
            for (long[] range : ranges)
            {
                long min = range[0];
                long max = range[1];
                if (ingredient >= min && ingredient <= max)
                {
                    count.getAndIncrement();
                    break;
                }
            }
        }
        return count.get();
    }
}
