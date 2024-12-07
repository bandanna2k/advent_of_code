package dnt.maths;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.BigDecimalUtils.isWholeNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class BigDecimalUtilsTest extends TestCase
{
    @Test
    public void testIsWholeNumber()
    {
        assertThat(isWholeNumber(new BigDecimal(11).divide(BigDecimal.TWO))).isFalse();
        assertThat(isWholeNumber(BigDecimal.ONE.divide(BigDecimal.TWO))).isFalse();
        assertThat(isWholeNumber(BigDecimal.TWO.divide(BigDecimal.ONE))).isTrue();
    }
}