package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;

import java.math.BigDecimal;
import java.time.Instant;

import static dnt.common.BigDecimalUtils.firstDivisibleNumberAfter;
import static dnt.common.BigDecimalUtils.isDivisible;
import static dnt.common.StringUtils.isPalindrome;
import static java.lang.Math.floorMod;
import static java.lang.Math.max;

public class CheckerOptimised16Plus implements Checker
{
    private final int length;
    private final BigDecimal divisor;
    private final Multiplier multiplier;
    private final int divisorLength;
    private final BigDecimal firstDivisible;
    private int palindromeCount = 0;

    public CheckerOptimised16Plus(int length, BigDecimal divisor)
    {
        this(length, divisor, firstDivisibleNumberAfter(new BigDecimal(getStartValue(length)), divisor.intValue()));
    }
    public CheckerOptimised16Plus(int length, BigDecimal divisor, BigDecimal firstDivisible)
    {
        assert length >= 16;
        assert BigDecimalUtils.isDivisible(firstDivisible, divisor);
        this.length = length;
        this.divisor = divisor;
        this.divisorLength = divisor.toString().length();
        this.multiplier = new MultiplierFor4Chars();
        this.firstDivisible = firstDivisible;
    }

    private static String getStartValue(int length)
    {
        String s = "1";
        for (int i = 0; i < length - 1; i++)
        {
            s += "0";
        }
        return s;
    }

    @Override
    public int getPalindromeCount()
    {
        return palindromeCount;
    }

    @Override
    public void go()
    {
        String nextString = firstDivisible.toString();
        while(true)
        {
            int a = Integer.parseInt(new String(new char[] {
                    nextString.charAt(3),  nextString.charAt(2), nextString.charAt(1), nextString.charAt(0) } )); // Swapped
            int d = Integer.parseInt(new String(new char[] {
                    nextString.charAt(length - 4), nextString.charAt(length - 3), nextString.charAt(length - 2), nextString.charAt(length - 1) } ));

            int b = Integer.parseInt(String.valueOf(nextString.charAt(divisorLength - 1)));
            int c = Integer.parseInt(String.valueOf(nextString.charAt(length - divisorLength))); // Swapped
//            System.out.printf("INFO:%s: %s\n", Instant.now(), nextString);
//            System.out.printf("INFO:%s: A:%d, B:%d, C:%d, D:%d\n", Instant.now(), a, b, c, d);

            if(a == d && b == c)
            {
//                System.out.printf("INFO:%s: Check is palindrome\n", Instant.now());
                if(isPalindrome(nextString))
                {
                    assert isDivisible(new BigDecimal(nextString), divisor);
                    System.out.printf("INFO:%s: Horah, Palindrome found. %s\n", Instant.now(), nextString);
                    this.palindromeCount++;
                }
            }

            int multiplierC = max(1, floorMod(b - c, 10));
            int multiplierD = multiplier.multiplier(d, a);
            BigDecimal MULTIPLIER = new BigDecimal(multiplierD);
//            MULTIPLIER = MULTIPLIER.add(new BigDecimal(multiplierC));
            BigDecimal next = new BigDecimal(nextString).add(divisor.multiply(MULTIPLIER));
            nextString = next.toString();
            if(nextString.length() > length)
            {
                break;
            }
        }
    }
}
