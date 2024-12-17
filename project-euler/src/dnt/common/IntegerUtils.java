package dnt.common;

public class IntegerUtils
{
    public static int reverseDigits(int n)
    {
        int revNum = 0;
        while (n > 0)
        {
            revNum = revNum * 10 + n % 10;
            n = n / 10;
        }
        return revNum;
    }
}
