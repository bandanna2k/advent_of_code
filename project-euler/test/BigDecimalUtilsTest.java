import org.junit.Test;

import java.math.BigDecimal;

import static dnt.common.BigDecimalUtils.isWholeNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class BigDecimalUtilsTest
{
    @Test
    public void testIsWholeNumber()
    {
        assertThat(isWholeNumber(new BigDecimal(11).divide(BigDecimal.TWO))).isFalse();
        assertThat(isWholeNumber(BigDecimal.ONE.divide(BigDecimal.TWO))).isFalse();
        assertThat(isWholeNumber(BigDecimal.TWO.divide(BigDecimal.ONE))).isTrue();
    }

    @Test
    public void name()
    {
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
    }
}