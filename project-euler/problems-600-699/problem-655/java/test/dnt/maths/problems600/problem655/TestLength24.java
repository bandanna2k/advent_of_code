package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorEven;
import dnt.maths.problems600.problem655.byQuarters.year2025.ModuliCalculatorCombined;
import dnt.maths.problems600.problem655.byQuarters.year2025.PalindromeExtractorCombinedEven;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Assertions.assertIsPalindromeAndDivisible;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength24
{
    @Test // 48 secs
    public void test24CharPalindromesOptimised()
    {
        int length = 24;
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, new BigDecimal("100000000000000000685900"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }

    @Test
    public void testLength24()
    {
        ModuliCalculatorImpl modCalculatorA = new ModuliCalculatorImpl(8, 17, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorB = new ModuliCalculatorImpl(4, 13, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorC = new ModuliCalculatorImpl(4, 9, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(modCalculatorA, modCalculatorB, modCalculatorC, modCalculatorD,
                bigPalindrome ->
                {
                    assertIsPalindromeAndDivisible(bigPalindrome);
                    System.out.println(bigPalindrome);
                });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(90410);
    }

    @Test
    public void testLength24Combined()
    {
        ModuliCalculatorImpl modCalculatorA = new ModuliCalculatorImpl(8, 17, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorB = new ModuliCalculatorImpl(4, 13, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorC = new ModuliCalculatorImpl(4, 9, BD10000019.intValue());
        ModuliCalculatorImpl modCalculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());
        ModuliCalculator calculatorAD = ModuliCalculatorCombined.create(modCalculatorA, modCalculatorD);
        ModuliCalculator calculatorBC = ModuliCalculatorCombined.create(modCalculatorB, modCalculatorC);

        PalindromeExtractorCombinedEven extractor = new PalindromeExtractorCombinedEven(calculatorAD, calculatorBC, p -> {});
        extractor.go();

        assertThat(extractor.getPalindromeCount()).isEqualTo(90410);
    }

}
