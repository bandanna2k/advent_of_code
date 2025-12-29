package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static dnt.maths.problems600.problem655.PalindromeUtils.isPalindrome;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TestLength19
{
    @Test
    public void test19CharUsingPalindromeExtractor()
    {
        ModuliCalculator moduliA = new ModuliCalculatorImpl(4, 16, BD10000019.intValue());
        ModuliCalculator moduliB = new ModuliCalculatorImpl(5, 11, BD10000019.intValue());
        ModuliCalculator moduliMid = new ModuliCalculatorImpl(1, 10, BD10000019.intValue());
        ModuliCalculator moduliC = new ModuliCalculatorImpl(5, 5, BD10000019.intValue());
        ModuliCalculator moduliD = new ModuliCalculatorImpl(4, 1, BD10000019.intValue());
        PalindromeExtractor extractor = new PalindromeExtractorOdd(moduliA, moduliB, moduliMid, moduliC, moduliD, x ->
        {
            assertTrue(isPalindrome(x));
            System.out.println(x);
        });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(924);
    }
}
