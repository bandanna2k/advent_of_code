package dnt.maths.problems600.problem655;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

@RunWith(Enclosed.class)
public class TestMultiplierGrid
{
    public static class TestMultiplier1Char
    {
        private MultiplierGridImpl grid = new MultiplierGridImpl(1, 1);

        @Test
        public void testToString()
        {
            System.out.println(grid);
        }

    }
    public static class TestMultiplier2Chars
    {
        private MultiplierGridImpl grid = new MultiplierGridImpl(2, 19);

        @Test
        public void testToString()
        {
            System.out.println(grid);
        }

        @Test
        public void testMultiplier()
        {
            assertThat(grid.multiplier(19, 10)).isEqualTo(89);
            assertThat(grid.multiplier(19, 01)).isEqualTo(78);
            assertThat(grid.multiplier(00, 19)).isEqualTo(1);
            assertThat(grid.multiplier(58, 77)).isEqualTo(1);
            assertThat(grid.multiplier(77, 96)).isEqualTo(1);
            assertThat(grid.multiplier(96, 15)).isEqualTo(1);

            assertThat(grid.multiplier(19, 19, 19)).isEqualTo(100);
            assertThat(grid.multiplier(19, 0, 0)).isEqualTo(100);

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
                    int result = source + (grid.divisor * multiplier);
                    result = result % 100;
                    assertThat(result)
                            .describedAs(String.format("Source:%d, Dest:%d, Result:%d", source, destination, result))
                            .isEqualTo(destination);
                }
            }
        }
    }

    public static class TestMultiplier3Chars
    {
        private MultiplierGridImpl grid = new MultiplierGridImpl(3, 19);

        @Test
        public void testToString()
        {
            System.out.println(grid);
        }

        @Test
        public void testMultiplier()
        {
            assertThat(grid.grid[19][10]).isEqualTo(789);
            assertThat(grid.grid[19][01]).isEqualTo(578);
            assertThat(grid.grid[00][19]).isEqualTo(1);
            assertThat(grid.grid[58][77]).isEqualTo(1);
            assertThat(grid.grid[77][96]).isEqualTo(1);
            assertThat(grid.grid[96][15]).isEqualTo(101);
            assertThat(grid.grid[158][177]).isEqualTo(1);

            assertThat(grid.multiplier(19, 19, 19)).isEqualTo(1000);
            assertThat(grid.multiplier(19, 0, 0)).isEqualTo(1000);

            {
                int divisor = 19;
                int test = 10;
                assertThat(test % divisor).isEqualTo(10);
                for (int i = 0; i < grid.sourceWidth; i++) {
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
                    int result = source + (grid.divisor * multiplier);
                    result = result % grid.sourceWidth;
                    assertThat(result)
                            .describedAs(String.format("Source:%d, Dest:%d, Result:%d", source, destination, result))
                            .isEqualTo(destination);
                }
            }
        }
    }

    public static class TestMultiplier4Chars
    {
        private MultiplierGridImpl grid = new MultiplierGridImpl(4, 19);

        @Test
        public void testToString()
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    System.out.printf("%04d ", grid.multiplier(i, j));
                }
                System.out.printf("\n");
            }
        }
    }

    public static class TestMultiplier5Chars
    {
        private MultiplierGridImpl grid = new MultiplierGridImpl(5, 19);

        @Test
        public void testToString()
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    System.out.printf("%05d ", grid.multiplier(i, j));
                }
                System.out.printf("\n");
            }
        }
    }
}
