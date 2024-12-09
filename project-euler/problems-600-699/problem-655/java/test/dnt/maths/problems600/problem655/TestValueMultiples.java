package dnt.maths.problems600.problem655;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.PalindromeUtils.isPalindrome;

public class TestValueMultiples
{
    @Test
    public void getFirstDivisiblePalindrome()
    {
        BigDecimal value = Constants.BD10000019;

        while(true)
        {
            value = value.add(Constants.BD10000019);
            if(isPalindrome(value))
            {
                System.out.printf("%s %d\n", value, String.valueOf(value).length());
                return;
            }
        }
    }
}
