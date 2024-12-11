package dnt.maths.problems600.problem655;

import dnt.common.BigDecimalUtils;

import java.math.BigDecimal;
import java.time.Instant;

import static dnt.common.BigDecimalUtils.firstDivisibleNumberAfter;
import static dnt.common.BigDecimalUtils.isDivisible;
import static dnt.common.StringUtils.isPalindrome;
import static java.lang.Math.floorMod;
import static java.lang.Math.max;

public class CheckerOptimised implements Checker
{
    private final int length;
    private final BigDecimal divisor;
    private final MultiplierGridImpl multiplierGrid;
    private final int divisorLength;
    private final BigDecimal firstDivisible;
    private int palindromeCount = 0;

    public CheckerOptimised(int length, BigDecimal divisor)
    {
        this(length, divisor, firstDivisibleNumberAfter(new BigDecimal(getStartValue(length)), divisor.intValue()));
    }
    public CheckerOptimised(int length, BigDecimal divisor, BigDecimal firstDivisible)
    {
        assert BigDecimalUtils.isDivisible(firstDivisible, divisor);
        this.length = length;
        this.divisor = divisor;
        this.divisorLength = divisor.toString().length();
        this.multiplierGrid = new MultiplierGridImpl(2, divisor.intValue());
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
            int a = Integer.parseInt(swap2chars(nextString));
            int d = Integer.parseInt(nextString.substring(length - 2, length));

            int b = Integer.parseInt(nextString.substring(divisorLength - 1, divisorLength));

            int cIndex = length - divisorLength;
            int c = Integer.parseInt(nextString.substring(cIndex, cIndex + 1));
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

            int multiplierC = max(1, floorMod(c - b, 10));
            int multiplierD = multiplierGrid.multiplier(d, a);
            BigDecimal MULTIPLIER = new BigDecimal(multiplierD).multiply(new BigDecimal(multiplierC));
            BigDecimal next = new BigDecimal(nextString).add(divisor.multiply(MULTIPLIER));
            nextString = next.toString();
            if(nextString.length() > length)
            {
                break;
            }
        }
    }

    private static String swap2chars(String input)
    {
        return new String(new char[] { input.charAt(1), input.charAt(0) });
    }
}
