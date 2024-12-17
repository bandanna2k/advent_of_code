package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorAD;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength16AndBelow
{
    @Test
    public void test8CharPalindromes()
    {
        int length = 8;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test
    public void test10CharPalindromes()
    {
        int length = 10;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test
    public void test12CharPalindromes()
    {
        int length = 12;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test
    public void test14CharPalindromes()
    {
        int length = 14;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test // Fails, tests too long a value
    @Ignore
    public void test16CharPalindromes()
    {
        int length = 16;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }

    @Test // 1 second
    public void test16CharPalindromesOptimised()
    {
        Instant start, now;
        System.out.printf("INFO:%s:Start go\n", start = Instant.now());

        int length = 16;
        Checker checker = new CheckerOptimised16Plus(length, BD10000019, new BigDecimal("1000000009996409"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);

        System.out.printf("INFO:%s:End %d\n", now = Instant.now(), Duration.between(start, now).toMillis());
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }

    @Test // 1 second
    public void test16CharUsingPalindromeExtractor()
    {
        Instant start, now;
        System.out.printf("INFO:%s:Start go\n", start = Instant.now());

        ModuliCalculator moduliA = new ModuliCalculatorImpl(8, 9, BD10000019.intValue());
        ModuliCalculator moduliD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());
        PalindromeExtractorAD extractorAD = new PalindromeExtractorAD(moduliA, moduliD, System.out::println);
        extractorAD.go();
        assertThat(extractorAD.getPalindromeCount()).isEqualTo(8);

        System.out.printf("INFO:%s:End %d\n", now = Instant.now(), Duration.between(start, now).toMillis());
    }
}
