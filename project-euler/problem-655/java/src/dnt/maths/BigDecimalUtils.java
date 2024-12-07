package dnt.maths;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public abstract class BigDecimalUtils
{
    public static boolean isWholeNumber(BigDecimal number)
    {
        return number.remainder(BigDecimal.ONE).compareTo(ZERO) == 0;
    }
}
