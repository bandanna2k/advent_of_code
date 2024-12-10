package dnt.common;

import java.math.BigDecimal;

public class StringUtils
{
    public static boolean isPalindrome(String string)
    {
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
