package dnt.maths.problems600.problem655;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.Constants.INT_BD10000019;
import static dnt.maths.problems600.problem655.PalindromeUtils.isPalindrome;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class Assertions
{
    public static void assertIsPalindromeAndDivisible(String input)
    {
        System.out.printf("(%d) %s%n", input.length(), input);
        assertIsPalindromeAndDivisible(new BigDecimal(input));
    }
    public static void assertIsPalindromeAndDivisible(BigDecimal test)
    {
        BigDecimal remainder = test.remainder(BD10000019);
        assertThat(remainder.compareTo(BigDecimal.ZERO))
                .describedAs("Not divisble. Remainder: " + remainder)
                .isEqualTo(0);
        assertTrue("Not palindrome " + test, isPalindrome(test));
    }

    public static void assertIsDivisible(long value) {
        long divided = value / INT_BD10000019;
        assertThat(divided * INT_BD10000019).isEqualTo(value);
    }
}
