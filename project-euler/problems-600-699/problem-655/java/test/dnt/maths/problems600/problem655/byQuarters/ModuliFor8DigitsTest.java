package dnt.maths.problems600.problem655.byQuarters;

import dnt.common.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class ModuliFor8DigitsTest
{
    @Test
    public void testOuter24to32()
    {
        ModuliFor8Digits moduli = new ModuliFor8Digits(25, BD10000019.intValue());

        System.out.println(moduli.get(0));
        System.out.println(moduli.get(1));
        System.out.println(moduli.get(2));
        System.out.println(moduli.get(10_000_000));
        System.out.println(moduli.get(10_000_001));
        System.out.println(moduli.get(10_000_002));

        System.out.println(new BigDecimal("1000000000000000000000000").remainder(BD10000019));
        System.out.println(new BigDecimal("2000000000000000000000000").remainder(BD10000019));
        System.out.println(BigDecimalUtils.createFirstBigDecimal(25).remainder(BD10000019));
        System.out.println(BigDecimalUtils.createFirstBigDecimal(25).multiply(BigDecimal.TWO).remainder(BD10000019));

        BigDecimal big25 = new BigDecimal("1000000000000000000000000");
        BigDecimal big8 = new BigDecimal("10000000");
        BigDecimal big32 = big25.multiply(big8);
        assertThat(big32.toString().length()).isEqualTo(32);
        System.out.println("big32 % divisor " + big32.remainder(BD10000019));

//        BigDecimal mod25 = big25.remainder(BD10000019);
//        System.out.println(mod25);
//        BigDecimal increment = mod25.multiply(big8);
//        System.out.println(increment);
//        BigDecimal mod32 = increment.remainder(BD10000019);
//        System.out.println(mod32);
//
//        System.out.println(big8.remainder(BD10000019));

        assertThat(new BigDecimal("1000000000000000000000000").remainder(BD10000019).intValue())
                .isEqualTo(3141019);

        assertThat(new BigDecimal("10000000000000000000000000000001").remainder(BD10000019).intValue())
                .isEqualTo(320753 + 1);
        assertThat(new BigDecimal("10000001000000000000000000000001").remainder(BD10000019).intValue())
                .isEqualTo(3461772 + 1);
        assertThat(new BigDecimal("10000002000000000000000000000001").remainder(BD10000019).intValue())
                .isEqualTo(6602791 + 1);
    }
}