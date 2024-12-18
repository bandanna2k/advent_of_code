package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorEven;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength20
{
    @Test // 52 secs /
    public void test20CharPalindromesOptimised()
    {
        int length = 20;
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, new BigDecimal("10000000000003900076"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(10);
    }

    @Test
    public void testLength18UsingPalindromeExtractor()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 13, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(2, 11, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(2, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                System.out::println);
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(726);
    }
}
