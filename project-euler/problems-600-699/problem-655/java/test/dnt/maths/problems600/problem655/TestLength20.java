package dnt.maths.problems600.problem655;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength20
{
    @Test // 48 secs
    public void test20CharPalindromesOptimised()
    {
        int length = 20;
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, new BigDecimal("10000000000003900076"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
