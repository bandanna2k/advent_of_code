package dnt.maths.problems600.problem655;

import org.junit.Test;

import static dnt.maths.problems600.problem655.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MultiplierFor2CharsTest
{
    @Test
    public void testMultiplierAgainstGrid()
    {
        int chars = 2;
        int sourceWidth = (int) Math.pow(10, chars);
        int destinationHeight = sourceWidth;
        int divisor = BD10000019.intValue();
        Multiplier multiplierGrid = new MultiplierGridImpl(2, divisor);
        Multiplier multiplier = new MultiplierFor2Chars();
        for (int source = 0; source < sourceWidth; source++)
        {
            for (int destination = 0; destination < destinationHeight; destination++)
            {
                int multiplierFromGrid = multiplierGrid.multiplier(source, destination);
                int result = source + (divisor * multiplierFromGrid);
                result = result % 100;
                assertThat(result)
                        .describedAs(String.format("Source:%d, Dest:%d, Result:%d", source, destination, result))
                        .isEqualTo(destination);

                int multiplierFromMultiplier = multiplier.multiplier(source, destination);
                assertThat(multiplierFromMultiplier).isEqualTo(multiplierFromGrid);
            }
        }
    }
}