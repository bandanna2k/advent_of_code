package dnt.maths.problems600.problem655;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestEvenChecker
{
    @Test
    public void test10CharPalindromes()
    {
        int length = 10;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(66429);
    }

    @Test
    public void test14CharPalindromes()
    {
        int length = 14;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
    }

    @Test
    public void testLoadTimings14() throws IOException
    {
        {
            CheckByPalindromeLoader checker = new CheckByPalindromeLoader(14, BD10000019);

            Instant start = Instant.now();
            checker.loadOrWritePalindromes();
            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("Test Count %d, Palindrome Count %d, Time(s): %d\n", checker.getPalindromeCount(), duration.getSeconds(), duration.getSeconds());

            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
        }
        {
            CheckByPalindromeLoader checker = new CheckByPalindromeLoader(14, BD10000019);

            Instant start = Instant.now();
            checker.loadOrWritePalindromes();
            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("Test Count %d, Palindrome Count %d, Time(s): %d\n", checker.getPalindromeCount(), duration.getSeconds(), duration.getSeconds());

            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
        }
    }

    @Test
    public void testDurationOfPalindromeCountX()
    {
        Instant start = Instant.now();
        Checker checker = new CheckByPalindromeEvenLength(20, null);
        checker.go();

        long testCount = checker.getPalindromeCount();
        int palindromeCount = checker.getPalindromeCount();

        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("Test Count %d, Palindrome Count %d, Time(s): %d\n", testCount, palindromeCount, duration.getSeconds());

        assertThat(checker.getPalindromeCount()).isEqualTo(0);
        assertThat(checker.getPalindromeCount()).isEqualTo(3922632450L);
    }

    @Test
    @Ignore
    public void showPalindromeCounts()
    {
        int palindromeCount = 0;
        for (int i = String.valueOf(BD10000019).length(); i < 32; i += 2)
        {
            Checker checker = new CheckByPalindromeEvenLength(i, BD10000019);
            checker.go();
            System.out.printf("Length: %d, Test Count: %d, Palindrome Count: %d, \n", i, checker.getPalindromeCount(), checker.getPalindromeCount());
            palindromeCount += checker.getPalindromeCount();
        }
        assertThat(palindromeCount).isEqualTo(10);
    }
}
