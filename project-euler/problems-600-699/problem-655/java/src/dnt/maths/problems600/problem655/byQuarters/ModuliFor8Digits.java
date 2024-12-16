package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static dnt.common.BigDecimalUtils.createFirstBigDecimal;

/**
 * Class to get modulus for outer values of large numbers.
 * E.g. Split a large number into 4 quarters and name them
 *      A + B + C + D
 * With this class we can determine the moduli for each
 * quarter and then add the moduli up to get the moduli
 * for the large number.
 */
public class ModuliFor8Digits extends BaseModuli
{
    private final List<BaseModuli.Record> modulusForA = new ArrayList<>();

    public ModuliFor8Digits(int charsFrom, int intDivisor)
    {
        System.out.printf("INFO:Start creating moduli class for chars %d to %d.\n", charsFrom, charsFrom + 7);

        BigDecimal bigA = createFirstBigDecimal(charsFrom);
        BigDecimal bigModA = bigA.remainder(new BigDecimal(intDivisor));

        int intModA = bigModA.intValue();
        int intModAIncrement = bigModA.intValue() % intDivisor;

        modulusForA.add(new BaseModuli.Record(0, 0));
        for (int a = 1; a < 100_000_000; a++)
        {
            modulusForA.add(new BaseModuli.Record(a, intModA));

            intModA = (intModA + intModAIncrement);
            intModA = intModA % intDivisor;
        }
        System.out.print("INFO:Finished creating moduli class.\n");
    }

    public void forEach(int chars, Consumer<Record> record)
    {
        int limit = (int) Math.pow(10, chars);
        for (int i = 0; i < limit; i++)
        {
            record.accept(this.modulusForA.get(i));
        }
    }

    public Record get(int i)
    {
        return modulusForA.get(i);
    }
}
