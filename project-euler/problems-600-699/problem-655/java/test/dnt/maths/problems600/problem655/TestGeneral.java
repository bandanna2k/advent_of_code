package dnt.maths.problems600.problem655;

import org.assertj.core.api.Condition;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static dnt.common.BigDecimalUtils.*;
import static dnt.maths.problems600.problem655.Assertions.assertIsDivisible;
import static dnt.maths.problems600.problem655.Assertions.assertIsPalindromeAndDivisible;
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
//        assertIsDivisible(216778101877612L);
//        assertIsPalindromeAndDivisible("216778101877612"); // 15
//
//        assertIsPalindromeAndDivisible("96399288088299369"); // 17

        assertIsPalindromeAndDivisible("99992154161116145129999"); // 23
        assertIsPalindromeAndDivisible("39506669129192196660593"); // 23

        assertIsPalindromeAndDivisible("10000000897393533539379800000001"); // 32
        assertIsPalindromeAndDivisible("10000000940776944967704900000001");
        assertIsPalindromeAndDivisible("10000001016677066077661010000001");
        assertIsPalindromeAndDivisible("10000001017159966995171010000001");
        assertIsPalindromeAndDivisible("10000001061171600617116010000001");
        assertIsPalindromeAndDivisible("10000000303875800857830300000001");
    }
}
