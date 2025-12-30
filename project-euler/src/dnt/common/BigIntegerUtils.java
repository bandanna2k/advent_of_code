package dnt.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;

public abstract class BigIntegerUtils
{
    public static BigInteger createFirstBigInteger(int chars)
    {
        StringBuilder sb = new StringBuilder("1");
        while(sb.length() < chars)
        {
            sb.append("0");
        }
        return new BigInteger(sb.toString());
    }
}
