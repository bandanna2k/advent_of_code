package dnt.adventofcode.day7;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day7Part2Test extends TestBase
{
    private static Stream<Arguments> inputs()
    {
        return Stream.of(
                Arguments.of("/day7test.txt", 40),
                Arguments.of("/day7real.txt", 76624086587804L)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException
    {
        List<String> grid = new ArrayList<>();
        try (InputStream inputStream = Day7Part2Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                grid.add(line);
            });
        }

        int width = grid.get(0).length();
        int height = grid.size();
        long[][] counts = new long[width][height];

        int originIndex = grid.getFirst().indexOf('S');
        counts[originIndex][0] = 1;

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                long count = counts[x][y];
                if(count > 0) {
                    char c = grid.get(y).charAt(x);
                    if(c == '^') {
                        long left = counts[x - 1][y + 1];
                        long right = counts[x + 1][y + 1];
                        counts[x - 1][y + 1] = left + count;
                        counts[x + 1][y + 1] = right + count;
                    } else {
                        long mid = counts[x][y + 1];
                        counts[x][y + 1] = mid + count;
                    }
                }
            }
        }

        long sum = 0;
        for (int x = 0; x < width; x++)
        {
            sum += counts[x][height-1];
        }
        assertThat(sum).isEqualTo(expected);
    }
}
