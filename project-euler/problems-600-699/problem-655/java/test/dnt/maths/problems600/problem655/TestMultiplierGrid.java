package dnt.maths.problems600.problem655;

import org.junit.Test;

import static dnt.maths.problems600.problem655.TestMultiplierGrid.MultiplierGrid.*;
import static org.assertj.core.api.Assertions.*;

public class TestMultiplierGrid
{
    private MultiplierGrid grid = new MultiplierGrid();;

    @Test
    public void testToString()
    {
        System.out.println(grid);
    }

    @Test
    public void testMultiplier()
    {
        assertThat(grid.grid[19][10]).isEqualTo(89);
        assertThat(grid.grid[19][01]).isEqualTo(78);
        assertThat(grid.grid[00][19]).isEqualTo(1);
        assertThat(grid.grid[58][77]).isEqualTo(1);
        assertThat(grid.grid[77][96]).isEqualTo(1);
        assertThat(grid.grid[96][15]).isEqualTo(1);

        assertThat(multiplier(19, 19, 19)).isEqualTo(100);
        assertThat(multiplier(19, 0, 0)).isEqualTo(100);

        {
            int divisor = 19;
            int test = 10;
            assertThat(test % divisor).isEqualTo(10);
            for (int i = 0; i < 100; i++) {
                test = test + divisor;
            }
            assertThat(test % divisor).isEqualTo(10);
        }
    }

    @Test
    public void testMultiplierGrid()
    {
        for (int source = 0; source < grid.sourceWidth; source++)
        {
            for (int destination = 0; destination < grid.destinationHeight; destination++)
            {
                int multiplier = grid.grid[source][destination];
                int result = source + (DIVISOR * multiplier);
                result = result % 100;
                assertThat(result)
                        .describedAs(String.format("Source:%d, Dest:%d, Result:%d", source, destination, result))
                        .isEqualTo(destination);
            }
        }
    }

    public static class MultiplierGrid {
        public static final int DIVISOR = 19;

        private int sourceWidth = 100;
        private int destinationHeight = 100;
        int[][] grid = new int[sourceWidth][destinationHeight];

        public MultiplierGrid()
        {
            for (int source = 0; source < grid.length; source++)
            {
                for (int destination = 0; destination < grid[0].length; destination++)
                {
                    grid[source][destination] = multiplier(DIVISOR, source, destination);
                }
            }
        }

        public static int multiplier(int divisor, int source, int destination) {
            int testSource = source + divisor;
            int counter = 1;
            while ((testSource % 100) != destination)
            {
                counter++;
                testSource += divisor;
            }
            return counter;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();

            int destinationCounter = 0;
            sb.append("     ");
            for (int i = 0; i < grid.length; i++) {
                sb.append(String.format("%02d ", destinationCounter++));
            }
            sb.append("\n");

            sb.append("     ");
            for (int i = 0; i < grid.length; i++) {
                sb.append("== ");
            }
            sb.append("\n");

            int sourceCounter = 0;
            for (int i = 0; i < grid.length; i++) {
                sb.append(String.format("%02d | ", sourceCounter++));
                for (int j = 0; j < grid[i].length; j++) {
                    sb.append(String.format("%02d ", grid[i][j]));
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }
}
