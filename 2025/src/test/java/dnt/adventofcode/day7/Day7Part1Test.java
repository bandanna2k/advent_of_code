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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class Day7Part1Test extends TestBase
{
    private static Stream<Arguments> inputs()
    {
        return Stream.of(
                Arguments.of("/day7test.txt", 21),
                Arguments.of("/day7real.txt", 1656)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException
    {
        List<String> grid = new ArrayList<>();
        try (InputStream inputStream = Day7Part1Test.class.getResourceAsStream(input))
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                grid.add(line);
            });
        }

        Set<Integer> beams = new HashSet<>();
        String first = grid.removeFirst();
        beams.add(first.indexOf("S"));

        int totalSplits = 0;
        for (int i = 1; i < grid.size(); i++)
        {
            System.out.println(beams);

            String currentLine = grid.get(i);

            Set<Integer> newBeams = new HashSet<>();

            Iterator<Integer> iterator = beams.iterator();
            while (iterator.hasNext()) {
                Integer beamAt = iterator.next();
                char beamChar =  currentLine.charAt(beamAt);
                if (beamChar == '^')
                {
                    totalSplits++;
                    iterator.remove();
                    newBeams.add(beamAt - 1);
                    newBeams.add(beamAt + 1);
                }
            }

            beams.addAll(newBeams);
        }

        assertThat(totalSplits).isEqualTo(expected);
    }
}
