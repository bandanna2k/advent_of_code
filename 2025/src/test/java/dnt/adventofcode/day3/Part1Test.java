package dnt.adventofcode.day3;

import dnt.adventofcode.TestBase;
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

public class Part1Test extends TestBase
{
    private static Stream<Arguments> part1() {
        return Stream.of(
                Arguments.of("/day3test.txt", 357),
                Arguments.of("/day3real.txt", 357)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("part1")
    void part1(final String input, final long expected) throws IOException
    {
        AtomicLong sum = new  AtomicLong(0);
        try (InputStream inputStream = Part1Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                long lineMax = Integer.MIN_VALUE;
                for (int i = 0; i < line.length() - 1; i++)
                {
                    char c1 = line.charAt(i);
                    long n1 = Character.getNumericValue(c1);

                    for (int j = i + 1; j < line.length(); j++)
                    {
                        char c2 = line.charAt(j);
                        long n2 = Character.getNumericValue(c2);

                        long number = (n1 * 10) + n2;

                        lineMax = Math.max(lineMax, number);
                    }
                }
                System.out.println(lineMax);
                sum.addAndGet(lineMax);
            });
        }
        assertThat(sum.get()).isEqualTo(expected);
    }
}
