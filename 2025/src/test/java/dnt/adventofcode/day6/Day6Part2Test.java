package dnt.adventofcode.day6;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Part2Test extends TestBase {
    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("/day6test.txt", 3263827),
                Arguments.of("/day6real.txt", 9695042567249L)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException {

        List<String> grid = new ArrayList<>();
        try (InputStream inputStream = Day6Part2Test.class.getResourceAsStream(input)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                grid.add(line);
            });
        }

        // Get max length
        int max = Integer.MIN_VALUE;
        for (int y = 0; y < grid.size(); y++)
        {
            String row = grid.get(y);
            max = Math.max(max, row.length());
        }
        // Pad all rows
        for (int y = 0; y < grid.size(); y++)
        {
            grid.set(y, rightPad(grid.get(y), max, ' '));
        }

        long total = 0;
        long prev = -1;
        BiFunction<Long, Long, Long> operation = null;
        for(int x = 0; x < max; x++)
        {
            char c = grid.getLast().charAt(x);
            switch (c) {
                case '+' ->
                {
                    prev = 0;
                    operation = Long::sum;
                }
                case '*' ->
                {
                    prev = 1;
                    operation = (aLong, aLong2) ->  aLong2 * aLong;
                }
                case ' ' -> {}
                default -> throw new IllegalArgumentException();
            }
            String s = "";
            for (int y = 0; y < grid.size() - 1; y++)
            {
                s += grid.get(y).charAt(x);
            }
            s = s.replace(" ", "");
            if(s.isEmpty())
            {
                total += prev;
            }
            else
            {
                prev = operation.apply(prev, Long.parseLong(s));
            }
        }
        total += prev;

        assertThat(total).isEqualTo(expected);
    }

    static String rightPad(String s, int totalLength, char padChar) {
        if (s.length() >= totalLength) {
            return s;
        }
        int numPads = totalLength - s.length();
        return s + String.valueOf(padChar).repeat(numPads);
    }
}
