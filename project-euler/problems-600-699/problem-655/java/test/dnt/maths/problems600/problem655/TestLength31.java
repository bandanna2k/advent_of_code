package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength31
{
    /*
    Needs -Xmx12g
     */
    @Test
    public void testLength31()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 24, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(7, 17, BD10000019.intValue());
        ModuliCalculator calculatorMid = new ModuliCalculatorImpl(1, 16, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(7, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorOdd(calculatorA, calculatorB, calculatorMid, calculatorC, calculatorD,
                System.out::println);
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(100);
    }
}
