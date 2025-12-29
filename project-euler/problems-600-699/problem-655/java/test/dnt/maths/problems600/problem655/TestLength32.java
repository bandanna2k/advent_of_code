package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import static dnt.common.BigDecimalUtils.isWholeNumber;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TestLength32
{
    @BeforeClass
    public static void needsXmx12g()
    {
        /* Needs -Xmx12g */
        long maxMemoryBytes = Runtime.getRuntime().maxMemory();
        long maxMemMb = ((maxMemoryBytes / 1000) / 1000);
        long maxMemGb = ((maxMemMb / 1000));
        assertThat(maxMemGb).isGreaterThanOrEqualTo(12);
    }

    @Test
    public void testLength32()
    {
        ModuliFor8Digits outer1to8 = new ModuliFor8Digits(1, BD10000019.intValue());
        ModuliFor8Digits outer9to16 = new ModuliFor8Digits(9, BD10000019.intValue());
        ModuliFor8Digits outer17to24 = new ModuliFor8Digits(17, BD10000019.intValue());
        ModuliFor8Digits outer25to32 = new ModuliFor8Digits(25, BD10000019.intValue());

        AtomicInteger counter = new AtomicInteger();
        System.out.println(Instant.now() + " Start");
        PalindromeExtractor extractor = new PalindromeExtractorEven(outer25to32, outer17to24, outer9to16, outer1to8,
                p ->
                {
                    System.out.println(Instant.now() + " First palindrome " + p);
                    if (counter.getAndIncrement() > 20)
                        throw new RuntimeException("Testing 20 palindromes.");
                });
        extractor.go();
        assertThat(extractor.getPalindromeCount()).isEqualTo(1);
    }

    @Test
    public void test32CharPalindromesOptimised()
    {
        int length = 32;
        BigDecimal firstDivisible = new BigDecimal("10000000000000000000000009679266");
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, firstDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
