package dnt.maths.problems600.problem655.byQuarters.year2025;

import dnt.maths.problems600.problem655.byQuarters.ModuliCalculator;

import java.math.BigInteger;

import static dnt.common.BigIntegerUtils.createFirstBigInteger;

/**
 * Class to get modulus for outer values of large numbers.
 * E.g. Split a large number into 4 quarters and name them
 *      A + B + C + D
 * With this class we can determine the moduli for each
 * quarter and then add the moduli up to get the moduli
 * for the large number.
 */
@Deprecated // WIP
public class ModuliCalculatorDual implements ModuliCalculator
{
    private final int[] moduli;
    private final int digitCount;

    public ModuliCalculatorDual(
            int digitCount,
            int charsFromA,
            int charsFromD,
            int intDivisor)
    {
        this.digitCount = digitCount;

        System.out.printf("INFO:Start creating moduli class for chars %d to %d.\n", charsFromA, charsFromA + digitCount - 1);
        System.out.printf("INFO:Start creating moduli class for chars %d to %d.\n", charsFromD, charsFromD + digitCount - 1);

        int arraySize = (int) Math.pow(10, digitCount);
        moduli = new int[arraySize];

        BigInteger bigA = createFirstBigInteger(charsFromA);
        BigInteger bigModA = bigA.mod(BigInteger.valueOf(intDivisor));
        BigInteger bigD = createFirstBigInteger(charsFromD);
        BigInteger bigModD = bigD.mod(BigInteger.valueOf(intDivisor));

        assert bigModA.intValue() != intDivisor;
        assert bigModD.intValue() != intDivisor;

        int intModA = bigModA.intValue();
        int intModD = bigModD.intValue();
        int intModAD = intModA + intModD;
        int intModADIncrement = (intModAD) % intDivisor;

        moduli[0] = 0;
        for (int a = 1; a < arraySize; a++)
        {
            moduli[a] = intModAD;

            intModAD = (intModAD + intModADIncrement);
            intModAD = intModAD % intDivisor;
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
