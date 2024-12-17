package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public abstract class BaseModuli
{
    static int getModIncrement(BigDecimal divisor, int chars)
    {
        StringBuilder sb = new StringBuilder("1");
        while(sb.length() < chars)
        {
            sb.append("0");
        }
        BigDecimal v = new BigDecimal(sb.toString());
        BigDecimal v2 = v.add(v);
        BigDecimal modV = v.divideAndRemainder(divisor)[1];
        BigDecimal modV2 = v2.divideAndRemainder(divisor)[1];
        BigDecimal modIncrement = modV2.subtract(modV).add(divisor);

        return modIncrement.intValue() % divisor.intValue();
    }

}
