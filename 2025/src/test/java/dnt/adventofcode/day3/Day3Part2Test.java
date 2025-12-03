package dnt.adventofcode.day3;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Part2Test extends TestBase
{
    private static Stream<Arguments> part1()
    {
        return Stream.of(
                Arguments.of("/day3test.txt", 3121910778619L),
                Arguments.of("/day3real.txt", 167549941654721L)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("part1")
    void part1(final String input, final long expected) throws IOException
    {
        AtomicLong sum = new AtomicLong(0);
        try (InputStream inputStream = Day3Part2Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                long running = 0;

                int maxIndex = -1;
                for (int i = 0; i < 12; i++)
                {
                    long maxValue = Long.MIN_VALUE;
                    for (int l1 = maxIndex + 1; l1 < line.length() - (11 - i); l1++)
                    {
                        char c1 = line.charAt(l1);
                        long n1 = Character.getNumericValue(c1);
                        if (n1 > maxValue)
                        {
                            maxIndex = l1;
                            maxValue = n1;
                        }
                    }
                    running *= 10;
                    running += maxValue;
                }

                System.out.println(running);
                sum.addAndGet(running);
            });
        }
        assertThat(sum.get()).isEqualTo(expected);
    }

    @Test
    void power()
    {
        assertThat(power(10, 0)).isEqualTo(1);
        assertThat(power(10, 1)).isEqualTo(10);
        assertThat(power(10, 2)).isEqualTo(100);
        assertThat(power(10, 3)).isEqualTo(1000);

    }
}
