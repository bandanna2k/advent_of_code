package dnt.maths.problems600.problem655;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static dnt.common.BigDecimalUtils.isWholeNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class TestGeneral
{
    @Test
    public void firstDivisibleNumberAfterX()
    {
        assertThat(firstDivisibleNumberAfter("10000000000000000", 10_000_019L))
                .isEqualTo(new BigDecimal("10000000000000000"));
    }

    private static BigDecimal firstDivisibleNumberAfter(String base, long divisor)
    {
        for (int i = 1; i < divisor; i++)
        {
            BigDecimal BASE = new BigDecimal(base).add(new BigDecimal(i)).setScale(8, RoundingMode.DOWN);
            BigDecimal DIVISOR = new BigDecimal(divisor);
            DIVISOR.setScale(8, RoundingMode.DOWN);
            BigDecimal divided = BASE.divide(DIVISOR, RoundingMode.DOWN);
            if(isWholeNumber(divided))
            {
                return BASE;
            }
        }
        throw new RuntimeException("Failed to find firstDivisibleNumber");
    }
}
