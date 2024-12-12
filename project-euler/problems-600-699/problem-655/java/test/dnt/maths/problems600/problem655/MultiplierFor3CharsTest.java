package dnt.maths.problems600.problem655;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiplierFor3CharsTest
{
    @Test
    public void testMultiplierAgainstGrid()
    {
        int chars = 3;
        int sourceWidth = (int) Math.pow(10, chars);
        int destinationHeight = sourceWidth;
        BigDecimal divisor = BD10000019;
        Multiplier multiplierGrid = new MultiplierGridImpl(chars, divisor.intValue());
        Multiplier multiplier = new MultiplierFor3Chars();
        for (int source = 0; source < sourceWidth; source++)
        {
            for (int destination = 0; destination < destinationHeight; destination++)
            {
                long multiplierFromGrid = multiplierGrid.multiplier(source, destination);
                long result = source + (divisor.longValue() * multiplierFromGrid);
                result = result % sourceWidth;
                assertThat(result)
                        .describedAs(String.format("Source:%d, Dest:%d, Result:%d", source, destination, result))
                        .isEqualTo(destination);

                int multiplierFromMultiplier = multiplier.multiplier(source, destination);
                assertThat(multiplierFromMultiplier).isEqualTo(multiplierFromGrid);
            }
        }
    }
}