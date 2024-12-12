package dnt.maths.problems600.problem655;

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
}
