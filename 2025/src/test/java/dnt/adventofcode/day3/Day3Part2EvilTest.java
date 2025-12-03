package dnt.adventofcode.day3;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Part2EvilTest extends TestBase
{
    private static Stream<Arguments> part1()
    {
        return Stream.of(
                Arguments.of("/day3test.txt", 357),
                Arguments.of("/day3real.trimmed.txt", 357)
        );
    }

//    @ParameterizedTest(name = "Expected {1}")
//    @MethodSource("part1")
    void part1(final String input, final long expected) throws IOException
    {
        AtomicLong sum = new AtomicLong(0);
        try (InputStream inputStream = Day3Part2EvilTest.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                long lineMax = Integer.MIN_VALUE;
                for (int l1 = 0; l1 < line.length() - 11; l1++)
                {
                    char c1 = line.charAt(l1);
                    long n1 = Character.getNumericValue(c1);

                    for (int l2 = l1 + 1; l2 < line.length() - 10; l2++)
                    {
                        char c2 = line.charAt(l2);
                        long n2 = Character.getNumericValue(c2);

                        for (int l3 = l2 + 1; l3 < line.length() - 9; l3++)
                        {
                            char c3 = line.charAt(l3);
                            long n3 = Character.getNumericValue(c3);

                            for (int l4 = l3 + 1; l4 < line.length() - 8; l4++)
                            {
                                char c4 = line.charAt(l4);
                                long n4 = Character.getNumericValue(c4);

                                for (int l5 = l4 + 1; l5 < line.length() - 7; l5++)
                                {
                                    char c5 = line.charAt(l5);
                                    long n5 = Character.getNumericValue(c5);

                                    for (int l6 = l5 + 1; l6 < line.length() - 6; l6++)
                                    {
                                        char c6 = line.charAt(l6);
                                        long n6 = Character.getNumericValue(c6);

                                        for (int l7 = l6 + 1; l7 < line.length() - 5; l7++)
                                        {
                                            char c7 = line.charAt(l7);
                                            long n7 = Character.getNumericValue(c7);

                                            for (int l8 = l7 + 1; l8 < line.length() - 4; l8++)
                                            {
                                                char c8 = line.charAt(l8);
                                                long n8 = Character.getNumericValue(c8);

                                                for (int l9 = l8 + 1; l9 < line.length() - 3; l9++)
                                                {
                                                    char c9 = line.charAt(l9);
                                                    long n9 = Character.getNumericValue(c9);

                                                    for (int l10 = l9 + 1; l10 < line.length() - 2; l10++)
                                                    {
                                                        char c10 = line.charAt(l10);
                                                        long n10 = Character.getNumericValue(c10);

                                                        for (int l11 = l10 + 1; l11 < line.length() - 1; l11++)
                                                        {
                                                            char c11 = line.charAt(l11);
                                                            long n11 = Character.getNumericValue(c11);

                                                            for (int l12 = l11 + 1; l12 < line.length(); l12++)
                                                            {
                                                                char c12 = line.charAt(l12);
                                                                long n12 = Character.getNumericValue(c12);

                                                                long number = (n1 * power(10, 11)) +
                                                                                (n2 * power(10, 10)) +
                                                                                (n3 * power(10, 9)) +
                                                                                (n4 * power(10, 8)) +
                                                                                (n5 * power(10, 7)) +
                                                                                (n6 * power(10, 6)) +
                                                                                (n7 * power(10, 5)) +
                                                                                (n8 * power(10, 4)) +
                                                                                (n9 * power(10, 3)) +
                                                                                (n10 * power(10, 2)) +
                                                                                (n11 * power(10, 1)) +
                                                                                (n12 * power(10, 0));
                                                                lineMax = Math.max(lineMax, number);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println(lineMax);
                sum.addAndGet(lineMax);
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
