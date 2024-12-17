package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dnt.common.BigDecimalUtils.createFirstBigDecimal;

/**
 * Class to get modulus for outer values of large numbers.
 * E.g. Split a large number into 4 quarters and name them
 *      A + B + C + D
 * With this class we can determine the moduli for each
 * quarter and then add the moduli up to get the moduli
 * for the large number.
 */
public class ModuliFor8Digits extends BaseModuli implements ModuliCalculator
{
    private final List<ModulusRecord> modulusForA = new ArrayList<>();

    public ModuliFor8Digits(int charsFrom, int intDivisor)
    {
        System.out.printf("INFO:Start creating moduli class for chars %d to %d.\n", charsFrom, charsFrom + 7);

        BigDecimal bigA = createFirstBigDecimal(charsFrom);
        BigDecimal bigModA = bigA.remainder(new BigDecimal(intDivisor));

        int intModA = bigModA.intValue();
        int intModAIncrement = bigModA.intValue() % intDivisor;

        modulusForA.add(new ModulusRecord(0, 0));
        for (int a = 1; a < 100_000_000; a++)
        {
            modulusForA.add(new ModulusRecord(a, intModA));

            intModA = (intModA + intModAIncrement);
            intModA = intModA % intDivisor;
        }
        System.out.print("INFO:Finished creating moduli class.\n");
    }

    public ModulusRecord get(int i)
    {
        return modulusForA.get(i);
    }

    public int getFirst()
    {
        return (int) Math.pow(10, 7);
    }

    public int getLast()
    {
        return ((int) Math.pow(10, 8)) - 1;
    }

    @Override
    public int getDigitCount()
    {
        return 8;
    }
}
