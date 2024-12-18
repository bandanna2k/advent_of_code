package dnt.maths.problems600.problem655;

import dnt.maths.problems600.problem655.byQuarters.*;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength17
{
    @Test
    public void test17CharPalindromes()
    {
        int length = 17;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(482);
    }

    @Test
    public void test17CharUsingPalindromeExtractor()
    {
        Instant start, now;
        System.out.printf("INFO:%s:Start go\n", start = Instant.now());

        ModuliCalculator moduliA = new ModuliCalculatorImpl(8, 10, BD10000019.intValue());
        ModuliCalculator moduliMid = new ModuliCalculatorImpl(1, 9, BD10000019.intValue());
        ModuliCalculator moduliD = new ModuliCalculatorImpl(8, 1, BD10000019.intValue());
        PalindromeExtractor extractorAD = new PalindromeExtractorOddAD(moduliA, moduliMid, moduliD, System.out::println);
        extractorAD.go();
        assertThat(extractorAD.getPalindromeCount()).isEqualTo(91);

        System.out.printf("INFO:%s:End %d\n", now = Instant.now(), Duration.between(start, now).toMillis());
    }
}
