package dnt.maths.problems600.problem655;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.common.BigDecimalUtils.firstDivisibleNumberAfter;
import static org.assertj.core.api.Assertions.assertThat;

public class TestGeneral
{
    @Test
    public void firstDivisibleNumberAfterX()
    {
        assertThat(firstDivisibleNumberAfter(new BigDecimal("10000000000000000"), 10_000_019))
                .isEqualTo(new BigDecimal("10000000000000000"));
    }
}
