package dnt.adventofcode.day5;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Part2Test extends TestBase
{
    private static Stream<Arguments> inputs()
    {
        return Stream.of(
                Arguments.of("/day5test.txt", 14),
                Arguments.of("/day5real.txt", 342433357244012L)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException
    {
        List<long[]> ranges = new ArrayList<>();
        Set<Long> ingredients = new HashSet<>();

        try (InputStream inputStream = Day5Part2Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                if (line.contains("-"))
                {
                    // Ranges
                    String[] split = line.split("-");
                    ranges.add(new long[]{
                            Long.parseLong(split[0]),
                            Long.parseLong(split[1])
                    });
                }
                else if (!line.trim().isEmpty())
                {
                    ingredients.add(Long.parseLong(line.trim()));
                }
            });
        }
//        System.out.println(ranges);
//        System.out.println(ingredients);

        boolean overlapExists;
        do
        {
            overlapExists = false;
            int i = -1;
            int j = -1;
            try
            {
                for (i = 0; i < ranges.size(); i++)
                {
                    for (j = 0; j < ranges.size(); j++)
                    {
                        if (i == j)
                        {
                            continue;
                        }

                        long[] range1 = ranges.get(i);
                        long[] range2 = ranges.get(j);

                        if (overlap(range1, range2))
                        {
                            ranges.add(new long[]{
                                    Math.min(range1[0], range2[0]),
                                    Math.max(range1[1], range2[1])
                            });
                            throw new OverlapExists();
                        }
                    }
                }
            }
            catch (OverlapExists e)
            {
                ranges.remove(Math.max(i,j));
                ranges.remove(Math.min(i,j));
                overlapExists = true;
            }
        }
        while (overlapExists);

        long sum = 0;
        for (long[] range : ranges)
        {
            sum += (range[1] - range[0] + 1);
        }
        assertThat(sum).isEqualTo(expected);
    }

    private boolean overlap(long[] range1, long[] range2)
    {
        long min1 = range1[0];
        long max1 = range1[1];
        long min2 = range2[0];
        long max2 = range2[1];

        // Overlaps
        if(min1 <= min2 && max1 <= max2 && max1 >= min2) return true;
        if(min2 <= min1 && max2 <= max1 && max2 >= min1) return true;

        // Contains
        if(min1 >= min2 && max1 <= max2) return true;
        if(min2 >= min1 && max2 <= max1) return true;

        return false;
    }

    private static class OverlapExists extends Exception
    {

    }

    private static Stream<Arguments> overlaps()
    {
        return Stream.of(
                Arguments.of(1, 2, 3, 4, false),
                Arguments.of(1, 2, 4, 5, false),
                Arguments.of(100, 200, 150, 250, true),
                Arguments.of(150, 250, 100, 200, true),

                Arguments.of(1, 11, 3, 4, true),

                Arguments.of(16, 20, 10, 14, false),
                Arguments.of(12, 18, 16, 20, true),

                Arguments.of(100, 200, 300, 400, false)
        );
    }

    @ParameterizedTest()
    @MethodSource("overlaps")
    void overlaps(long min1, long max1, long min2, long max2, boolean expected)
    {
        assertThat(overlap(
                new long[] { min1, max1 },
                new long[] { min2, max2 }
        )).isEqualTo(expected);
        assertThat(overlap(
                new long[] { min2, max2 },
                new long[] { min1, max1 }
        )).isEqualTo(expected);
    }
}
