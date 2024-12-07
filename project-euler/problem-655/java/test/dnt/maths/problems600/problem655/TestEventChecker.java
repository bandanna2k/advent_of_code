package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.EvenChecker;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class TestEventChecker
{
    private static final BigDecimal BD10000019 = new BigDecimal(10_000_019);

    @Test
    public void test10CharPalindromes()
    {
        int length = 10;
        EvenChecker checker = new EvenChecker(length, BD10000019);
        checker.go();
        assertThat(checker.getTestCount()).isEqualTo(66429);
    }

    @Test
    public void test14CharPalindromes()
    {
        int length = 14;
        EvenChecker checker = new EvenChecker(length, BD10000019);
        checker.go();
        assertThat(checker.getTestCount()).isEqualTo(5380839);
    }

    @Test
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
