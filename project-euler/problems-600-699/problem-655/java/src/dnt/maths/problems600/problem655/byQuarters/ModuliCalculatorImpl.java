package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ModuliCalculatorImpl implements ModuliCalculator
{
    private final int[] moduli;
    private final int digitCount;

    public ModuliCalculatorImpl(int digitCount, int charsFrom, int intDivisor)
    {
        this.digitCount = digitCount;

        System.out.printf("INFO:Start creating moduli class for chars %d to %d.\n", charsFrom, charsFrom + digitCount - 1);

        int arraySize = (int) Math.pow(10, digitCount);
        moduli = new int[arraySize];

        BigDecimal bigA = createFirstBigDecimal(charsFrom);
        BigDecimal bigModA = bigA.remainder(new BigDecimal(intDivisor));

        assert bigModA.intValue() != intDivisor;

        int intModA = bigModA.intValue();
        int intModAIncrement = bigModA.intValue() % intDivisor;

        moduli[0] = 0;
        for (int a = 1; a < arraySize; a++)
        {
            moduli[a] = intModA;

            intModA = (intModA + intModAIncrement);
            intModA = intModA % intDivisor;
        }
        System.out.printf("INFO:Finished creating moduli class. %d %d %n",
                moduli[0], moduli[moduli.length - 1]);
    }

    public int get(int i)
    {
        return moduli[i];
    }

    @Override
    public int getDigitCount()
    {
        return digitCount;
    }

    public int getFirst()
    {
        return (int) Math.pow(10, digitCount - 1);
    }

    public int getLast()
    {
        return ((int) Math.pow(10, digitCount)) - 1;
    }

    @Override
    public String toString()
    {
        return "ModuliCalculatorImpl{" +
                "moduli.length=" + moduli.length +
                ", digitCount=" + digitCount +
                "} " + super.toString();
    }
}
