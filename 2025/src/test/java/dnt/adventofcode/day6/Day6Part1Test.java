package dnt.adventofcode.day6;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Part1Test extends TestBase {
    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("/day6test.txt", 4277556),
                Arguments.of("/day6real.txt", 5060053676136L)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException {

        List<String[]> grid = new ArrayList<>();
        try (InputStream inputStream = Day6Part1Test.class.getResourceAsStream(input)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                String[] split = line.split(" ");
                List<String> collect = Arrays.stream(split).filter(s -> !s.isEmpty()).toList();
                grid.add(collect.toArray(new String[0]));
            });
        }

        String[] operations = grid.removeLast();

        long total = 0;
        for (int x = 0; x < operations.length; x++)
        {
            BiFunction<Long, Long, Long> operation;
            long prev;
            switch (operations[x]) {
                case "+" ->
                {
                    prev = 0;
                    operation = Long::sum;
                }
                case "*" ->
                {
                    prev = 1;
                    operation = (aLong, aLong2) ->  aLong2 * aLong;
                }
                default -> throw new IllegalArgumentException();
            }
            for (int y = 0; y < grid.size(); y++)
            {
                long value = Long.parseLong(grid.get(y)[x]);

                prev = operation.apply(prev, value);
            }
            total += prev;
        }

        assertThat(total).isEqualTo(expected);
    }
}
