package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength18
{
    @Test //
    public void test18CharPalindromes()
    {
        int length = 18;
        Checker checker = new CheckByPalindromeEvenLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }

    @Test // 48 secs / 1.5 secs
    public void test18CharPalindromesOptimised()
    {
        int length = 18;
        Checker checker = new CheckerOptimised16Plus(length, BD10000019, new BigDecimal("100000000009639019"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }

    /*
Needs -Xmx12g
 */
    @Test
    public void testLength18UsingPalindromeExtractor()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 11, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(1, 10, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(1, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                System.out::println);
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(101);
    }
}
