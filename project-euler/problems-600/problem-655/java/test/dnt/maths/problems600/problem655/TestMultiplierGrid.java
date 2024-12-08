package dnt.maths.problems600.problem655;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TestMultiplierGrid
{
    @Test
    public void testMultiplierFrom18to19() {
        final int divisor = 19;
        final int source = 25;
        int testSource = 25;
        int counter = 0;
        int destination = 26;
        while ((testSource % 100) != destination) {
            counter++;
            testSource += divisor;
        }
        System.out.println(testSource);
        System.out.println(counter);
        System.out.println(source + (divisor * counter));
    }

    public int multiplier(int divisor, int source, int destination) {
        int testSource = 25;
        int counter = 0;
        while ((testSource % 100) != destination) {
            counter++;
            testSource += divisor;
        }
        return counter;
    }

    @Test
    public void testMultiplierGrid() {
        MultiplierGrid grid = new MultiplierGrid();
        System.out.println(grid);

        assertThat(grid.grid[58][77]).isEqualTo(1);
        assertThat(grid.grid[77][96]).isEqualTo(1);
        assertThat(grid.grid[96][15]).isEqualTo(1);
    }

    public static class MultiplierGrid {
        private static final int DIVISOR = 19;

        private static int sourceWidth = 100;
        private static int destinationHeight = 100;
        int[][] grid = new int[sourceWidth][destinationHeight];

        public MultiplierGrid() {
//            int cells = sourceWidth * destinationHeight;
//
//            int counter = 0;
//            int multiplier = 0;
//
//            int source = 0;
//            int destination = 0;
//            while(counter++ < cells)
//            {
//                source = source + DIVISOR;
//                multiplier = (multiplier + 1) % DIVISOR;
//
//                if(source >= destinationHeight)
//                {
//                    source = source % sourceWidth;
//                    destination = (destination + 1) % destinationHeight;
//                }
//
//                assert grid[source][destination] == 0;
//                grid[source][destination] = multiplier;
//            }

            for (int source = 0; source < grid.length; source++)
            {
                for (int destination = 0; destination < grid[0].length; destination++)
                {
                    grid[source][destination] = multiplier(DIVISOR, source, destination);
                }
            }
        }

        public int multiplier(int divisor, int source, int destination) {
            int testSource = 25;
            int counter = 0;
            while ((testSource % 100) != destination) {
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
