package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import dnt.maths.problems600.problem655.byQuarters.year2025.PalindromeExtractorMultiThreadedEven;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

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
        Progress progress = Progress.getAndStart();

        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 11, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(1, 10, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(1, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                x ->
                {
                    progress.progress(x);
                    System.out.println(x);
                });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(101);
    }

    @Test
    public void testLength18UsingPalindromeExtractor2()
    {
        Progress progress = Progress.getAndStart();

        ModuliCalculator calculatorAD = new ModuliCalculatorDual(8, 11, 1, BD10000019.intValue());
        ModuliCalculator calculatorBC = new ModuliCalculatorDual(1, 10, 9, BD10000019.intValue());

        prototype("1000 0000 00 0000 0001".replace(" ", ""));
        System.out.println(calculatorAD.get(10_000_000));

        prototype("0000 0001 00 1000 0000".replace(" ", ""));
        System.out.println(calculatorAD.get(1));

//        assertThat(0).isEqualTo(101);
    }
    private static void prototype(String number)
    {
        BigInteger value = new BigInteger(number);
        BigInteger mod = value.mod(Constants.BI_BD10000019);
        System.out.println(mod);
    }

    @Test
    public void testDivisorX4()
    {
        int divisor = BD10000019.intValue();
        divisor *= 4;
        assertThat((long)divisor).isEqualTo(BD10000019.longValue()*4);
    }
}
