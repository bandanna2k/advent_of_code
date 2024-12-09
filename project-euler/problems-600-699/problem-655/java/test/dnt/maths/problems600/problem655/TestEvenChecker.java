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

    @Test // 5 Seconds
    public void test16CharPalindromes()
    {
        int length = 16;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }

    @Test // Not getting right results
    public void test18CharPalindromes()
    {
        int length = 18;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }

    @Test
    public void test16CharPalindromesOptimised()
    {
        Instant start, now;
        System.out.printf("INFO:%s:Start go\n", start = Instant.now());

        int length = 16;
        Checker checker = new CheckerOptimised(length, BD10000019, new BigDecimal("1000000009996409"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);

        System.out.printf("INFO:%s:End %d\n", now = Instant.now(), Duration.between(start, now).toMillis());
    }

    @Test
    public void test18CharPalindromesOptimised()
    {
        int length = 18;
        Checker checker = new CheckerOptimised(length, BD10000019, new BigDecimal("100000000009639019"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }

    @Test
    public void testLoadTimings14() throws IOException
    {
        {
            CheckByPalindromeEvenLength checker = new CheckByPalindromeEvenLength(14, BD10000019);

            Instant start = Instant.now();
            checker.loadOrWritePalindromes();
            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("Test Count %d, Palindrome Count %d, Time(s): %d\n", checker.getPalindromeCount(), duration.getSeconds(), duration.getSeconds());

            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
        }
        {
            CheckByPalindromeEvenLength checker = new CheckByPalindromeEvenLength(14, BD10000019);

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
