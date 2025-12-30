package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.year2025.ModuliCalculatorCombined;
import dnt.maths.problems600.problem655.byQuarters.year2025.PalindromeExtractorCombinedEven;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength26
{
    @Test
    public void testLength26()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 19, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(5, 14, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(5, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());
        ModuliCalculator calculatorAD = ModuliCalculatorCombined.create(calculatorA, calculatorD);
        ModuliCalculator calculatorBC = ModuliCalculatorCombined.create(calculatorB, calculatorC);

        PalindromeExtractorCombinedEven extractor = new PalindromeExtractorCombinedEven(calculatorAD, calculatorBC, p -> {});
        extractor.go();

        assertThat(extractor.getPalindromeCount()).isEqualTo(-1);
    }
}
