package dnt.maths.problems600.problem655;

import java.math.BigDecimal;

public abstract class PalindromeUtils
{
    public static boolean isPalindrome(BigDecimal value)
    {
        return isPalindromeByStringCompare(value);
    }

    static boolean isPalindromeByStringCompare(BigDecimal value)
    {
        String string = value.toString();
        int half = string.length() / 2;
        for (int i = 0, j = string.length() - 1; i < half; i++, j--)
        {
            char a = string.charAt(i);
            char z = string.charAt(j);
            if(a != z) return false;
        }
        return true;
    }
}
