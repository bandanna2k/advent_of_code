package dnt.maths.problems600.problem655;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.PalindromeUtils.*;
import static org.assertj.core.api.Assertions.*;

public class PalindromeUtilsTest
{
    @Test
    public void testIsPalindromeByString()
    {
        assertThat(isPalindromeByStringCompare(new BigDecimal("1234321"))).isTrue();
        assertThat(isPalindromeByStringCompare(new BigDecimal("12344321"))).isTrue();
        assertThat(isPalindromeByStringCompare(new BigDecimal("1"))).isTrue();
        assertThat(isPalindromeByStringCompare(new BigDecimal("11"))).isTrue();
        assertThat(isPalindromeByStringCompare(new BigDecimal("1000000001"))).isTrue();

        assertThat(isPalindromeByStringCompare(new BigDecimal("123421"))).isFalse();
        assertThat(isPalindromeByStringCompare(new BigDecimal("12"))).isFalse();
        assertThat(isPalindromeByStringCompare(new BigDecimal("100002"))).isFalse();
    }
}
