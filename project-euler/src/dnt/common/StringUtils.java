package dnt.common;

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

    public static String reverse(String s)
    {
        String reversedStr = "";
        for (int i = 0; i < s.length(); i++) {
            reversedStr = s.charAt(i) + reversedStr;
        }
        return reversedStr;
    }
}
