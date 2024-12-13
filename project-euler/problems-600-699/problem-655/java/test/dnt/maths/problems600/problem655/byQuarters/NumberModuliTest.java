package dnt.maths.problems600.problem655.byQuarters;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberModuliTest
{
    private final NumberModuli numberModuli = new NumberModuli();
    private long palindromeCount = 0;

    @Test
    public void testModuli()
    {
        assertThat(numberModuli.getModulusAD(10_000_000)).isEqualTo(1_202_057);
        assertThat(numberModuli.getModulusBC(10_000_000)).isEqualTo(1238155L);

        long bd10000019 = BD10000019.longValue();
        numberModuli.forEachAD(recordAD -> {
            numberModuli.forEachBC(recordBC -> {
                long mod = recordAD.modulus() + recordBC.modulus();
                if(mod == 0 || mod == bd10000019)
                {
                    System.out.printf("Palindrome found: AD:%s, BC:%s, Mod:%d\n", recordAD, recordBC, mod);
                    palindromeCount++;
                }
            });
        });
        assertThat(palindromeCount).isEqualTo(1_202_056);
    }

    @Test
    public void testModDeltas()
    {
        BigDecimal firstValue = new BigDecimal("1000000000000000000000000000001");

    }
}