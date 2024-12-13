package dnt.maths.problems600.problem655.byQuarters;

import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.as;
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
    public void testLongCharLength()
    {
        System.out.printf("%d Len:%d\n", Long.MAX_VALUE, String.valueOf(Long.MAX_VALUE).length());
    }

    @Test
    public void testModDeltas()
    {
        {
            BigDecimal value1 = new BigDecimal("01234567012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234568012345670123456701234567");
            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            BigDecimal increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 25, mod delta = %s\n", increment);
        }
        {
            BigDecimal value1 = new BigDecimal("01234567012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234567012345680123456701234567");
            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            BigDecimal increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 17mod delta = %s\n", increment);
        }
        {
            BigDecimal value1 = new BigDecimal("01234567012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234567012345670123456801234567");
            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            BigDecimal increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 8mod delta = %s\n", increment);
        }
        {
            BigDecimal value1 = new BigDecimal("01234567012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234567012345670123456701234568");
            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            BigDecimal increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 1mod delta = %s\n", increment);
        }
    }

    @Test
    public void testAddingModDeltas()
    {
        BigDecimal increment;
        {
            BigDecimal value1 = new BigDecimal("01234567012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234568012345670123456701234567");

            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 25, mod delta = %s\n", increment);

            assertThat(value1ModN.add(increment).divideAndRemainder(BD10000019)[1].longValue())
                    .isEqualTo(value2ModN.divideAndRemainder(BD10000019)[1].longValue());
        }
        {
            BigDecimal value1 = new BigDecimal("01234568012345670123456701234567");
            BigDecimal value2 = new BigDecimal("01234569012345670123456701234567");

            BigDecimal value1ModN = value1.divideAndRemainder(BD10000019)[1];
            BigDecimal value2ModN = value2.divideAndRemainder(BD10000019)[1];
            System.out.printf("%s modN = %s\n", value1, value1ModN);
            System.out.printf("%s modN = %s\n", value2, value2ModN);
            increment = value2ModN.subtract(value1ModN).add(BD10000019).divideAndRemainder(BD10000019)[1];
            System.out.printf("Pos 25, mod delta = %s\n", increment);

            assertThat(value1ModN.add(increment).divideAndRemainder(BD10000019)[1].longValue())
                    .isEqualTo(value2ModN.divideAndRemainder(BD10000019)[1].longValue());
        }
    }
}