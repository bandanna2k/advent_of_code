package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorOdd;
import org.junit.Test;

import static dnt.maths.problems600.problem655.Assertions.assertIsPalindromeAndDivisible;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength21
{
    @Test
    public void test21CharUsingPalindromeExtractor()
    {
        ModuliCalculator moduliA = new ModuliCalculatorImpl(5, 17, BD10000019.intValue());
        ModuliCalculator moduliB = new ModuliCalculatorImpl(5, 12, BD10000019.intValue());
        ModuliCalculator moduliMid = new ModuliCalculatorImpl(1, 11, BD10000019.intValue());
        ModuliCalculator moduliC = new ModuliCalculatorImpl(5, 6, BD10000019.intValue());
        ModuliCalculator moduliD = new ModuliCalculatorImpl(5, 1, BD10000019.intValue());
        PalindromeExtractor extractor = new PalindromeExtractorOdd(moduliA, moduliB, moduliMid, moduliC, moduliD, x ->
        {
            assertIsPalindromeAndDivisible(x);
            System.out.println(x);
        });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(8956L);
    }
}
