package dnt.maths.problems600.problem655;

import org.junit.Test;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class TestLength15AndBelow
{
    @Test // 0.2 secs
    public void test9CharPalindromes()
    {
        int length = 9;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test // 0.9 secs
    public void test11CharPalindromes()
    {
        int length = 11;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(0);
    }

    @Test // 4.2 secs
    public void test13CharPalindromes()
    {
        int length = 13;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(6);
    }

    @Test // 43 secs
    public void test15CharPalindromes()
    {
        int length = 15;
        Checker checker = new CheckByPalindromeOddLength(length, BD10000019);
        checker.go();
        assertThat(checker.getPalindromeCount()).isEqualTo(8);
    }
}
