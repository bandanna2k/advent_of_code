package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;
import dnt.maths.problems600.problem655.byQuarters.ModuliCalculatorImpl;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractor;
import dnt.maths.problems600.problem655.byQuarters.PalindromeExtractorEven;
import dnt.maths.problems600.problem655.byQuarters.year2025.PalindromeExtractorMultiThreadedEven;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

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

    /*
    1x CPU (1 min, 28 secs)
     */
    @Test
    public void testLength20UsingPalindromeExtractor()
    {
        Progress progress = Progress.getAndStart();
        AtomicInteger count = new AtomicInteger(0);

        ModuliCalculator calculatorA = new ModuliCalculatorImpl(8, 13, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(2, 11, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(2, 9, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorMultiThreadedEven(calculatorA, calculatorB, calculatorC, calculatorD,
                x ->
                {
                    progress.progress(x);
                    count.incrementAndGet();
                    System.out.println(x);
                });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(711);
    }

    @Test // 54 secs
    public void testLength20UsingPalindromeExtractor2()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(7, 14, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(3, 11, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(3, 8, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(7, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                System.out::println);
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(711);
    }

    @Test // 1 min, 19 secs
    public void testLength20UsingPalindromeExtractor3()
    {
        ModuliCalculator calculatorA = new ModuliCalculatorImpl(5, 16, BD10000019.intValue());
        ModuliCalculator calculatorB = new ModuliCalculatorImpl(5, 11, BD10000019.intValue());
        ModuliCalculator calculatorC = new ModuliCalculatorImpl(5, 6, BD10000019.intValue());
        ModuliCalculator calculatorD = new ModuliCalculatorImpl(5, 1, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractorEven(calculatorA, calculatorB, calculatorC, calculatorD,
                System.out::println);
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(711);
    }
}
