package dnt.adventofcode.day4;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Part1Test extends TestBase
{
    private static Stream<Arguments> part1() {
        return Stream.of(
                Arguments.of("/day4test.txt", 1),
                Arguments.of("/day4real.txt", 1)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("part1")
    void part1(final String input, final long expected) throws IOException
    {
        try (InputStream inputStream = Day4Part1Test.class.getResourceAsStream(input))
        {
            AtomicInteger x = new AtomicInteger();
            AtomicInteger y = new AtomicInteger();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                y.getAndIncrement();
                x.set(line.length());
            });
            int[][] grid = new int[x.get()][y.get()];


        }
    }
}
