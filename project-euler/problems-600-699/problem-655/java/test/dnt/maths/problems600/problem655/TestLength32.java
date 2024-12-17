package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength32
{
    /*
    Needs -Xmx12g
     */
    @Test
    public void testLength32()
    {
        ModuliFor8Digits outer1to8 = new ModuliFor8Digits(1, BD10000019.intValue());
        ModuliFor8Digits outer9to16 = new ModuliFor8Digits(9, BD10000019.intValue());
        ModuliFor8Digits outer17to24 = new ModuliFor8Digits(17, BD10000019.intValue());
        ModuliFor8Digits outer25to32 = new ModuliFor8Digits(25, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractor(outer25to32, outer17to24, outer9to16, outer1to8, p -> {});
        extractor.go();
    }

    @Test
    public void testLength32_2()
    {
        ModuliCalculatorImpl outer1to8 = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());
        ModuliCalculatorImpl outer9to16 = new ModuliCalculatorImpl(8, 9, BD10000019.intValue());
        ModuliCalculatorImpl outer17to24 = new ModuliCalculatorImpl(8, 17, BD10000019.intValue());
        ModuliCalculatorImpl outer25to32 = new ModuliCalculatorImpl(8, 25, BD10000019.intValue());

        PalindromeExtractor extractor = new PalindromeExtractor(outer25to32, outer17to24, outer9to16, outer1to8, p -> {});
        extractor.go();
    }

    @Test // 48 secs
    public void test32CharPalindromesOptimised()
    {
        int length = 32;
        BigDecimal firstDivisible = new BigDecimal("10000000000000000000000009679266");
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, firstDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
