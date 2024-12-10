package dnt.common;

import java.math.BigDecimal;

public class StringUtils
{
    public static boolean isPalindrome(String input)
    {
        int half = input.length() / 2;
        for (int i = 0, j = input.length() - 1; i < half; i++, j--)
        {
            char a = input.charAt(i);
            char z = input.charAt(j);
            if(a != z) return false;
        }
        return true;
    }

}
