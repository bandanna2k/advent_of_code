package dnt.maths.problems600.problem655.byQuarters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class Outer24to32Moduli extends BaseModuli
{
    private final List<BaseModuli.Record> modulusForAandD = new ArrayList<>();

    private final BigDecimal bigDivisor = BD10000019;
    private final int intDivisor = BD10000019.intValue();
    private final long longDivisor = BD10000019.longValue();


    public Outer24to32Moduli()
    {
        int modAIncrement = getModIncrement(bigDivisor, 32);
        long modALong = (10_000_000L * (long)modAIncrement) % longDivisor;
        int modA = (int)modALong;

        int modDIncrement = getModIncrement(bigDivisor, 1);
        int modD = 1;

        for (int a = 10_000_000; a < 100_000_000; a++)
        {
            modulusForAandD.add(new BaseModuli.Record(a, modA + modD));

            modA = (modA + modAIncrement);
            modD = (modD + modDIncrement);

            if(modA >= intDivisor) modA = 0;
            if(modD >= intDivisor) modD = 0;
        }
    }

    public void forEach(Consumer<Record> record)
    {
        modulusForAandD.forEach(record);
    }
}
