package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorOdd;
import org.junit.Ignore;
import org.junit.Test;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength15AndBelow
{
    @Test
    public void test15CharUsingPalindromeExtractor()
    {
        ModuliCalculator moduliA = new ModuliCalculatorImpl(3, 13, BD10000019.intValue());
        ModuliCalculator moduliB = new ModuliCalculatorImpl(4, 9, BD10000019.intValue());
        ModuliCalculator moduliMid = new ModuliCalculatorImpl(1, 8, BD10000019.intValue());
        ModuliCalculator moduliC = new ModuliCalculatorImpl(4, 4, BD10000019.intValue());
        ModuliCalculator moduliD = new ModuliCalculatorImpl(3, 1, BD10000019.intValue());
        PalindromeExtractor extractor = new PalindromeExtractorOdd(moduliA, moduliB, moduliMid, moduliC, moduliD, x ->
        {
            Assertions.assertIsPalindromeAndDivisible(x);
            System.out.println(x);
        });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(0);
    }

    @Ignore
    @Test // 0.2 secs
    public void test9CharPalindromes()
    {
        int length = 9;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019, Assertions::assertIsPalindromeAndDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Ignore
    @Test // 0.9 secs
    public void test11CharPalindromes()
    {
        int length = 11;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019, Assertions::assertIsPalindromeAndDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Ignore
    @Test // 4.2 secs
    public void test13CharPalindromes()
    {
        int length = 13;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019, Assertions::assertIsPalindromeAndDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Ignore
    @Test // 43 secs
    public void test15CharPalindromes()
    {
        int length = 15;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019, Assertions::assertIsPalindromeAndDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(48);
    }
}
