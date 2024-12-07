package dnt.maths.problems600.problem655;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOddChecker
{
    private static final BigDecimal BD10000019 = new BigDecimal(10_000_019);

    @Test
    public void test9CharPalindromes()
    {
        int length = 9;
        OddChecker checker = new OddChecker(length, BD10000019);
        checker.go();
        assertThat(checker.getTestCount()).isEqualTo(66429);
    }

    @Test
    @Ignore
    public void showPalindromeCounts()
    {
        int palindromeCount = 0;
        for (int i = String.valueOf(BD10000019).length(); i < 32; i += 2)
        {
            EvenChecker checker = new EvenChecker(i, BD10000019);
            checker.go();
            System.out.printf("Length: %d, Test Count: %d, Palindrome Count: %d, \n", i, checker.getTestCount(), checker.getPalindromeCount());
            palindromeCount += checker.getPalindromeCount();
        }
        assertThat(palindromeCount).isEqualTo(10);
    }
}
