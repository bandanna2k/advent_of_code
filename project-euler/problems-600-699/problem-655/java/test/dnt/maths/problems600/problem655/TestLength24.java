package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.common.BigDecimalUtils.*;
import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength24
{
    @Test // 48 secs
    public void test20CharPalindromesOptimised()
    {
        int length = 24;
        Checker checker = new CheckerOptimised24Plus(length, BD10000019, new BigDecimal("100000000000000000685900"));
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(101);
    }
}
