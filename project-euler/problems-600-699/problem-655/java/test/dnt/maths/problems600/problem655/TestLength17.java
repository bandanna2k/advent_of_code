package dnt.maths.problems600.problem655;

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
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }

//    @Test
//    public void test17CharPalindromesOptimised()
//    {
//        Instant start, now;
//        System.out.printf("INFO:%s:Start go\n", start = Instant.now());
//
//        int length = 17;
//        Checker checker = new CheckerOptimised17Plus(length, BD10000019, new BigDecimal("1000000009996409"));
//        checker.go();
//        assertThat(checker.getPalindromeCount()).isEqualTo(8);
//
//        System.out.printf("INFO:%s:End %d\n", now = Instant.now(), Duration.between(start, now).toMillis());
//        assertThat(checker.getPalindromeCount()).isEqualTo(8);
//    }
}
