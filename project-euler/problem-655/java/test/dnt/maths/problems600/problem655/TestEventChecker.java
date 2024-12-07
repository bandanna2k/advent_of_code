package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.EvenChecker;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static java.math.BigDecimal.ZERO;
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
        assertThat(checker.getPalindromeCount()).isEqualTo(66429);
    }

    @Test
    public void test14CharPalindromes()
    {
        int length = 14;
        EvenChecker checker = new EvenChecker(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
    }

    @Test
    public void testLoadTimings14() throws IOException
    {
        {
            EvenChecker checker = new EvenChecker(14, BD10000019);

            Instant start = Instant.now();
            checker.loadOrWritePalindromes();
            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("Test Count %d, Palindrome Count %d, Time(s): %d\n", checker.getPalindromeCount(), duration.getSeconds(), duration.getSeconds());

            assertThat(checker.getPalindromeCount()).isEqualTo(5380839);
        }
        {
            EvenChecker checker = new EvenChecker(14, BD10000019);

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
        EvenChecker checker = new EvenChecker(20, null);
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
            EvenChecker checker = new EvenChecker(i, BD10000019);
            checker.go();
            System.out.printf("Length: %d, Test Count: %d, Palindrome Count: %d, \n", i, checker.getPalindromeCount(), checker.getPalindromeCount());
            palindromeCount += checker.getPalindromeCount();
        }
        assertThat(palindromeCount).isEqualTo(10);
    }
}
