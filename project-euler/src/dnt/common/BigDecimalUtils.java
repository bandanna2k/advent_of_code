package dnt.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;

public abstract class BigDecimalUtils
{
    public static boolean isWholeNumber(BigDecimal number)
    {
        return number.remainder(BigDecimal.ONE).compareTo(ZERO) == 0;
    }

    public static boolean isDivisible(BigDecimal number, BigDecimal divisor)
    {
        BigDecimal scaled = new BigDecimal(number.toString()).setScale(10);
        return isWholeNumber(scaled.divide(divisor, RoundingMode.DOWN));
    }

    public static BigDecimal firstDivisibleNumberAfter(BigDecimal base, int divisor)
    {
        for (int i = 1; i < divisor; i++)
        {
            BigDecimal BASE = base.add(new BigDecimal(i)).setScale(10, RoundingMode.DOWN);
            BigDecimal DIVISOR = new BigDecimal(divisor);
            BigDecimal divided = BASE.divide(DIVISOR, RoundingMode.DOWN);
            if(isWholeNumber(divided))
            {
                return BASE;
            }
        }
        throw new RuntimeException("Failed to find firstDivisibleNumber");
    }

    public static BigDecimal createFirstBigDecimal(int chars)
    {
        StringBuilder sb = new StringBuilder("1");
        while(sb.length() < chars)
        {
            sb.append("0");
        }
        return new BigDecimal(sb.toString());
    }
}
