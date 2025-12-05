package dnt.adventofcode.day4;

import dnt.adventofcode.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Part2Test extends TestBase {
    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("/day4test.txt", 43),
                Arguments.of("/day4real.txt", 9182)
        );
    }

    @ParameterizedTest(name = "Expected {1}")
    @MethodSource("inputs")
    void test(final String input, final long expected) throws IOException {
        final char[][] grid;
        AtomicInteger sizeX = new AtomicInteger();
        AtomicInteger sizeY = new AtomicInteger();
        try (InputStream inputStream = Day4Part2Test.class.getResourceAsStream(input)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                sizeX.getAndIncrement();
                sizeY.set(line.length());
            });
            grid = new char[sizeY.get()][sizeX.get()];
        }
        try (InputStream inputStream = Day4Part2Test.class.getResourceAsStream(input)) {
            AtomicInteger x = new AtomicInteger();
            AtomicInteger y = new AtomicInteger();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines().forEach(line ->
            {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    //grid[i][y.get()] = c;
                    grid[y.get()][i] = c;
                }
                y.getAndIncrement();
            });
        }
        int totalCount = 0;

        Set<int[]> toBeRemoved = new HashSet<>();
        do {
            toBeRemoved.forEach(ints -> grid[ints[0]][ints[1]] = ' ');
            toBeRemoved.clear();

            for (int x = 0; x < sizeX.get(); x++) {
                for (int y = 0; y < sizeY.get(); y++) {
                    if (grid[x][y] == '@') {

                        int count = 0;
                        count += (check(grid, x - 1, y - 1) ? 1 : 0);
                        count += (check(grid, x, y - 1) ? 1 : 0);
                        count += (check(grid, x + 1, y - 1) ? 1 : 0);

                        count += (check(grid, x - 1, y) ? 1 : 0);
                        count += (check(grid, x + 1, y) ? 1 : 0);

                        count += (check(grid, x - 1, y + 1) ? 1 : 0);
                        count += (check(grid, x, y + 1) ? 1 : 0);
                        count += (check(grid, x + 1, y + 1) ? 1 : 0);

                        if (count < 4) {
                            grid[x][y] = 'x';
                            toBeRemoved.add(new int[]{x, y});
                            totalCount++;
                        }
                    }
                }
            }
        }
        while(!toBeRemoved.isEmpty());

        assertThat(totalCount).isEqualTo(expected);
    }

    private static boolean check(char[][] grid, int x, int y) {
        try {
            return grid[x][y] == '@' || grid[x][y] == 'x';
        } catch (Exception e) {
            return false;
        }
    }
}
