package dnt.maths.problems600.problem655.byQuarters.year2025;

import dnt.maths.problems600.problem655.Constants;
import dnt.maths.problems600.problem655.ReverseDigits;
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
public class ModuliCalculatorCombined implements ModuliCalculator
{
    private final int[] moduli;
    private final int digitCount;

    public static ModuliCalculatorCombined create(ModuliCalculator calcLeft, ModuliCalculator calcRight) {
        assert calcLeft.getDigitCount() == calcRight.getDigitCount();
        int digitCount = calcLeft.getDigitCount();
        ModuliCalculatorCombined combined = new ModuliCalculatorCombined(digitCount);

        ReverseDigits reverseDigits = ReverseDigits.getReverseDigits(digitCount);
        for (int left = 0; left < combined.size(); left++)
        {
            int right = reverseDigits.reverseDigits[left];
            combined.moduli[left] = (calcLeft.get(left) + calcRight.get(right)) % Constants.INT_BD10000019;
        }
        return combined;
    }
    private ModuliCalculatorCombined(int digitCount)
    {
        this.digitCount = digitCount;
        this.moduli = new int[size()];
    }

    private int size()
    {
        return (int) Math.pow(10, digitCount);
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
