package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorEven;
import dnt.maths.problems600.problem655.byQuarters.year2025.ModuliCalculatorCombined;
import dnt.maths.problems600.problem655.byQuarters.year2025.PalindromeExtractorCombinedEven;
import org.junit.Test;

import static dnt.maths.problems600.problem655.Assertions.assertIsPalindromeAndDivisible;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength22
{
    @Test
    public void testLength22UsingPalindromeExtractor()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 15, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(3, 12, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(3, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                x ->
                {
                    Assertions.assertIsPalindromeAndDivisible(x);
                    System.out.println(x);
                });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(8974L);
    }

    @Test
    public void testLength22UsingPalindromeExtractor2()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 15, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(3, 12, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(3, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        ModuliCalculator calculatorAD = ModuliCalculatorCombined.create(calculatorA, calculatorD);
        ModuliCalculator calculatorBC = ModuliCalculatorCombined.create(calculatorB, calculatorC);

        PalindromeExtractorCombinedEven extractor = new PalindromeExtractorCombinedEven(calculatorAD, calculatorBC, p -> {});
        extractor.go();

        assertThat(extractor.getPalindromeCount()).isEqualTo(8974L);
    }
}
