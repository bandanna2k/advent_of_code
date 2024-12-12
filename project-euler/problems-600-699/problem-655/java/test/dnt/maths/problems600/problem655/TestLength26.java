package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength26
{
    @Test
    public void test26CharPalindromesOptimised()
    {
        int length = 26;
        BigDecimal firstDivisible = new BigDecimal("10000000000000000008589886");
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, firstDivisible);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
