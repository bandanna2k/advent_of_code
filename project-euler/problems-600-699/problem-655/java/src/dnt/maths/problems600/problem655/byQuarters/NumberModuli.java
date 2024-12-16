package dnt.maths.problems600.problem655.byQuarters;

import dnt.common.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static dnt.maths.problems600.problem655.Constants.BD10000019;

public class NumberModuli
{
    private Pair<Integer, Integer> moduli2;

    private final List<Record> modulusForAandD = new ArrayList<>();
    private final List<Record> modulusForBandC = new ArrayList<>();

    public NumberModuli()
    {
        long divisor = BD10000019.longValue();

        BigDecimal A = new BigDecimal("10000000000000000000000000000001");
        BigDecimal A2 = new BigDecimal("10000001000000000000000000000001");

        BigDecimal B = new BigDecimal("100000000000000000000000");
        BigDecimal B2 = new BigDecimal("100000000000000000000000");

        long modA = A.divideAndRemainder(BD10000019)[1].longValue();
        long modB = 0;
        long modC = 0;
        long modD = 1;

        long modAincrement = (A2.subtract(A)).divideAndRemainder(BD10000019)[1].longValue();
        long modBincrement = (B2.subtract(B)).divideAndRemainder(BD10000019)[1].longValue();

        for (int i = 10_000_000; i < 100_000_000; i++)
        {
            // A and D
            {
                modulusForAandD.add(new Record(i, (modA + modD) % divisor));
                if(modA > divisor)
                {
                    modA = 0;
                }
                if(modD > divisor)
                {
                    modD = 0;
                }
                modA = (modA + modAincrement) % divisor;
                modD++;
            }

            // B and C
            {
                modulusForBandC.add(new Record(i, (modB + modC) % divisor));
                if(modB > divisor)
                {
                    modB = 0;
                }
                if(modC > divisor)
                {
                    modC = 0;
                }
                modB = (modB + modBincrement) % divisor;
                modC++;
            }
        }
    }

    public long getModulusAD(int number)
    {
        return modulusForAandD.get(number).modulus;
    }

    public long getModulusBC(int number)
    {
        return modulusForBandC.get(number).modulus;
    }

    public void forEachAD(Consumer<Record> consumer)
    {
        modulusForAandD.forEach(consumer);
    }

    public void forEachBC(Consumer<Record> consumer)
    {
        modulusForBandC.forEach(consumer);
    }

    public record Record(int number, long modulus) {}
}
