package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static dnt.common.BigDecimalUtils.createFirstBigDecimal;
import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class Outer24to32Moduli extends BaseModuli
{
    private final List<BaseModuli.Record> modulusForAandD = new ArrayList<>();

    private final BigDecimal bigDivisor = BD10000019;
    private final int intDivisor = BD10000019.intValue();


    public Outer24to32Moduli()
    {
        BigDecimal bigA = createFirstBigDecimal(25);
        BigDecimal bigModA = bigA.remainder(bigDivisor);

        int intModA = bigModA.intValue();

        BigDecimal bigIncrement = bigModA;
        int intModAIncrement = bigIncrement.intValue() % intDivisor;

        modulusForAandD.add(new BaseModuli.Record(0, 0));
        for (int a = 1; a < 100_000_000; a++)
        {
            modulusForAandD.add(new BaseModuli.Record(a, intModA));

            intModA = (intModA + intModAIncrement);
            intModA = intModA % intDivisor;
        }
    }

    public void forEach(Consumer<Record> record, int chars)
    {
        int limit = (int) Math.pow(10, chars);
        for (int i = 0; i < limit; i++)
        {
            record.accept(this.modulusForAandD.get(i));
        }
    }
}
