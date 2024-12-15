package dnt.maths.problems600.problem655.byQuarters;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static dnt.maths.problems600.problem655.Constants.BD10000019;
import static org.assertj.core.api.Assertions.assertThat;

public class Outer24to32ModuliTest
{
    @Test
    public void testOuter24to32()
    {
        Outer24to32Moduli moduli = new Outer24to32Moduli();
        AtomicInteger counter = new AtomicInteger(0);
//        moduli.forEach(record -> {
//            System.out.println(record);
//            if(counter.getAndIncrement() > 5) Assertions.fail();
//        });
        assertThat(new BigDecimal("10000002000000000000000010000002").divideAndRemainder(BD10000019)[1])
                .isEqualTo(4547221);
    }
}