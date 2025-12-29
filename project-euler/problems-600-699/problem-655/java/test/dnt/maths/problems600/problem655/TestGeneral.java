package dnt.maths.problems600.problem655;

import org.assertj.core.api.Condition;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static dnt.common.BigDecimalUtils.*;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.PalindromeUtils.isPalindrome;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TestGeneral
{
    @Test
    public void firstDivisibleNumberAfterX()
    {
        System.out.println(Instant.now() + " Start");
        BigDecimal firstDivisibleNumberAfter = firstDivisibleNumberAfter(new BigDecimal("10000000000000000"), 10_000_019);
        System.out.println(Instant.now() + " After easy");
        BigDecimal firstDivisibleNumberAfterINEFFICIENT = firstDivisibleNumberAfterINEFFICIENT(new BigDecimal("10000000000000000"), 10_000_019);
        System.out.println(Instant.now() + " After inefficient");

        assertThat(firstDivisibleNumberAfter).has(BigDecimalCondition.comparator(new BigDecimal("10000000009963919")));
        assertThat(firstDivisibleNumberAfterINEFFICIENT).has(BigDecimalCondition.comparator(new BigDecimal("10000000009963919")));
//        assertThat(firstDivisibleNumberAfter).isEqualTo(new BigDecimal("10000000009963919"));
//        assertThat(firstDivisibleNumberAfterINEFFICIENT).isEqualTo(new BigDecimal("10000000009963919"));
    }

    private static class BigDecimalCondition extends Condition<BigDecimal>
    {
        private static BigDecimalCondition comparator(BigDecimal value) { return new BigDecimalCondition(value); }

        private final BigDecimal value;

        private BigDecimalCondition(BigDecimal value)
        {
            this.value = value;
        }

        @Override
        public boolean matches(BigDecimal s)
        {
            return s.compareTo(value) == 0;
        }
    }

    @Test
    public void testPalindromes()
    {
        assertIsPalindromeDivisible("10000000303875800857830300000001");
    }

    private static void assertIsPalindromeDivisible(String input)
    {
        BigDecimal test = new BigDecimal(input);
        BigDecimal divided = test.divide(BD10000019);
        assertTrue(isWholeNumber(divided));
        assertTrue(isPalindrome(test));
    }
}
